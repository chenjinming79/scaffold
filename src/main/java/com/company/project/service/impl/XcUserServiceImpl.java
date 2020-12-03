package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.XcSysUserMapper;
import com.company.project.dao.XcUserMapper;
import com.company.project.model.XcSysUser;
import com.company.project.model.XcUser;
import com.company.project.service.XcUserService;
import com.company.project.utils.*;
import com.company.project.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wf.captcha.GifCaptcha;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * Created by CodeGenerator on 2020/11/18.
 */
@Service
@Transactional
public class XcUserServiceImpl extends AbstractService<XcUser> implements XcUserService {
    @Resource
    private XcUserMapper xcUserMapper;

    @Resource
    private XcSysUserMapper sysUserMapper;

    @Autowired
    private RedisService redisService;

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public Result login(LoginVo vo) {

        readWriteLock.writeLock().lock();

        try {
            VerfiyCodeVo verfiyCodeVo = (VerfiyCodeVo) redisService.get(Constant.REDIS_KEY_VERFIY + vo.getVerifyKey());
            if(null == verfiyCodeVo){
                return ResultGenerator.genFailResult(ResultCode.VERFIY_TOKEN_ERROR,"图形验证码token不存在");
            }

            if(!vo.getVerifyCode().toLowerCase().equals(verfiyCodeVo.getCode().toLowerCase())){
                return ResultGenerator.genFailResult(ResultCode.VERFIY_CODE_ERROR,"验证码输入错误");
            }

            long curTime = System.currentTimeMillis();

            if (curTime >  verfiyCodeVo.getExpireTime()) {
                Logger.info(this, "curTime is " + curTime + " validTime is " + redisService.expire(verfiyCodeVo.getCode()));
                redisService.delete(verfiyCodeVo.getCode());
                return ResultGenerator.genFailResult(ResultCode.VERFIY_CODE_TIME_ERROR,"验证码已经过期");
            }

            redisService.delete(verfiyCodeVo.getCode());

            if ("1".equals(vo.getChannel())){
                //1代表用户登录
                return userLogin(vo);
            }else if ("2".equals(vo.getChannel())){
                //2代表后台用户登录
                return sysUserLogin(vo);
            }
        } catch (Exception e){
            e.printStackTrace();
            Logger.info(this,"登录错误：" + e.getMessage());
        }finally {
            if(readWriteLock.isWriteLocked()){
                readWriteLock.writeLock().unlock();
            }
        }
        return ResultGenerator.genFailResult(ResultCode.USER_LOGIN_CHANNEL_ERROR,"登录渠道不存在，请重新登录");
    }

    /**
     * 后台用户登录
     * @param vo
     * @return
     */
    public Result sysUserLogin(LoginVo vo){

        SysUserVo sysUserVo = new SysUserVo();

        XcSysUser xcSysUser = sysUserMapper.findSysUserByPhone(vo.getPhone());
        if (null == xcSysUser){
            return ResultGenerator.genFailResult(ResultCode.USER_NOT_EXIST,"用户信息不存在[账号可能被停用或删除]");
        }

        if(!Md5Utils.getMd5(vo.getPassword()).equals(xcSysUser.getPassword())){
            return ResultGenerator.genFailResult(ResultCode.PASSWORD_ERROR,"密码输入错误，请重新输入");
        }

        //获取token
        String token = (String) redisService.get(xcSysUser.getId() + vo.getChannel() + "USERID");

        Boolean loginFlag = false;

        if(StringUtils.isNotBlank(token)){
            //说明已登陆，或直接断网
            redisService.delete(Constant.REDIS_KEY_LOGIN + token);
            redisService.delete(xcSysUser.getId() + vo.getChannel() + "USERID");
        }else{
            //true首次
            loginFlag=true;
        }
        //创建token
        token = TokenUtil.getToken();

        try {
            sysUserVo.setUserId(xcSysUser.getId());
            sysUserVo.setPhone(xcSysUser.getPhone());
            sysUserVo.setToken(token);
            sysUserVo.setExpireTime(2505600000L);
            sysUserVo.setRoleId(xcSysUser.getRoleId());
            sysUserVo.setChannel(vo.getChannel());
            sysUserVo.setUserName(xcSysUser.getUserName());
            //redisService.put(Constant.REDIS_KEY_LOGIN, token, new RedisModel(su.getId(), System.currentTimeMillis() + magConfig.getExpireTime()), magConfig.getExpireTime());
            redisService.setWithExpire(Constant.REDIS_KEY_LOGIN + token, sysUserVo , 2505600000L);
            redisService.set(xcSysUser.getId() + vo.getChannel() + "USERID",token);
        }catch (Exception e){
            e.printStackTrace();
            Logger.info(this,"登录token存入redis产生异常："+e.getMessage());
            throw new RuntimeException("存入redis异常");
        }
        return ResultGenerator.genSuccessResult(sysUserVo);
    }

    /**
     * 用户登录
     * @param vo
     * @return
     */
    public Result userLogin(LoginVo vo){

        SysUserVo sysUserVo = new SysUserVo();

        XcUser xcUser = xcUserMapper.findUserByPhone(vo.getPhone());
        if (null == xcUser){
            return ResultGenerator.genFailResult(ResultCode.USER_NOT_EXIST,"用户信息不存在[账号可能被停用或删除]");
        }

        if(!Md5Utils.getMd5(vo.getPassword()).equals(xcUser.getPassword())){
            return ResultGenerator.genFailResult(ResultCode.PASSWORD_ERROR,"密码输入错误，请重新输入");
        }

        //创建token
        String token= (String) redisService.get(xcUser.getId() + "USERID");

        Boolean loginFlag = false;

        if(StringUtils.isNotBlank(token)){
            //说明已登陆，或直接断网
            redisService.delete(Constant.REDIS_KEY_LOGIN + token);
            redisService.delete(xcUser.getId()+"USERID");
        }else{
            //true首次
            loginFlag=true;
        }
        token = TokenUtil.getToken();

        try {
            sysUserVo.setUserId(xcUser.getId());
            sysUserVo.setPhone(xcUser.getPhone());
            sysUserVo.setToken(token);
            sysUserVo.setExpireTime(2505600000L);
            sysUserVo.setChannel(vo.getChannel());
            sysUserVo.setUserName(xcUser.getUserName());
            //redisService.put(Constant.REDIS_KEY_LOGIN, token, new RedisModel(su.getId(), System.currentTimeMillis() + magConfig.getExpireTime()), magConfig.getExpireTime());
            redisService.setWithExpire(Constant.REDIS_KEY_LOGIN + token, sysUserVo , 2505600000L);
            redisService.set(xcUser.getId()+"USERID",token);
        }catch (Exception e){
            e.printStackTrace();
            Logger.info(this,"登录token存入redis产生异常："+e.getMessage());
            throw new RuntimeException("存入redis异常");
        }
        return ResultGenerator.genSuccessResult(sysUserVo);
    }

    /**
     * 用户退出
     * @param userId
     * @return
     */
    @Override
    public Result logout(Long userId) {


        UserVo userVo = null;
        String token=(String)redisService.get(userId+"USERID");
        try {
            userVo = (UserVo)redisService.get(Constant.REDIS_KEY_LOGIN + token);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("redis异常");
        }

        redisService.delete(userId+"USERID");

        if (userVo != null){
            redisService.delete(Constant.REDIS_KEY_LOGIN + token);

            return ResultGenerator.genSuccessResult();
        }

        return ResultGenerator.genFailResult(ResultCode.NOT_LOGIN_EXCEPTION,"用户未登录,请重新登录");
    }

    @Override
    public String getUserPhoneById(String createUserId) {
        return xcUserMapper.getUserPhoneById(createUserId);
    }

    @Override
    public Result list(Integer page, Integer size, XcUser xcUser) {
        PageHelper.startPage(page, size);
        List<XcUser> list = xcUserMapper.list(xcUser);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 生成验证码
     * @return
     */
    @Override
    public Result captcha() {
        GifCaptcha specCaptcha = new GifCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        String verifyToken = TokenUtil.getToken();
        // 存入redis并设置过期时间为30秒
        redisService.setWithExpire(Constant.REDIS_KEY_VERFIY + verifyToken, new VerfiyCodeVo(verCode,System.currentTimeMillis() + Constant.verifyCodeForTempValidTime)  , Constant.verifyCodeForTempValidTime);
        CaptchaVo captchaVo = new CaptchaVo();
        captchaVo.setVerifyToken(verifyToken);
        captchaVo.setData(specCaptcha.toBase64());
        // 将key和base64返回给前端
        return ResultGenerator.genSuccessResult(captchaVo);
    }
}
