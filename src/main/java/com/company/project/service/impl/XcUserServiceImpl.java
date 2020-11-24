package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.XcUserMapper;
import com.company.project.model.XcUser;
import com.company.project.service.XcUserService;
import com.company.project.utils.*;
import com.company.project.vo.CaptchaVo;
import com.company.project.vo.LoginVo;
import com.company.project.vo.UserVo;
import com.company.project.vo.VerfiyCodeVo;
import com.wf.captcha.GifCaptcha;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * Created by CodeGenerator on 2020/11/18.
 */
@Service
@Transactional
public class XcUserServiceImpl extends AbstractService<XcUser> implements XcUserService {
    @Resource
    private XcUserMapper tUserMapper;

    @Autowired
    private RedisService redisService;

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public Result login(LoginVo vo) {

        readWriteLock.writeLock().lock();

        UserVo userVo = new UserVo();

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

            XcUser xcUser = tUserMapper.findUserByPhone(vo.getPhone());
            if (null == xcUser){
                return ResultGenerator.genFailResult(ResultCode.USER_NOT_EXIST,"用户信息不存在[账号可能被停用或删除]");
            }

            if(!Md5Utils.getMd5(vo.getPassword()).equals(xcUser.getPassword())){
                return ResultGenerator.genFailResult(ResultCode.PASSWORD_ERROR,"密码输入错误，请重新输入");
            }

            //创建token
            String token= (String) redisService.get(xcUser.getId()+"USERID");

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
                userVo.setUserId(xcUser.getId());
                userVo.setPhone(xcUser.getPhone());
                userVo.setToken(token);
                userVo.setExpireTime(2505600000L);
                //redisService.put(Constant.REDIS_KEY_LOGIN, token, new RedisModel(su.getId(), System.currentTimeMillis() + magConfig.getExpireTime()), magConfig.getExpireTime());
                redisService.setWithExpire(Constant.REDIS_KEY_LOGIN + token, userVo , 2505600000L);
                redisService.set(xcUser.getId()+"USERID",token);
            }catch (Exception e){
                e.printStackTrace();
                Logger.info(this,"登录token存入redis产生异常："+e.getMessage());
                throw new RuntimeException("存入redis异常");
            }
        }/*catch (VerfiyTokenException e) {
            Logger.error(this, "图形验证码token不存在：", e);
            throw new VerfiyTokenException(loginParam.getVerifyToken());
        }catch(VerfiyCodeException e){
            Logger.error(this, "图形验证不存在:", e);
            throw new VerfiyCodeException(loginParam.getVerifyCode());
        }catch(OutTimeTokenException e){
            Logger.error(this, "token过期:", e);
            throw new OutTimeTokenException();
        }*/ finally{
            if(readWriteLock.isWriteLocked()){
                readWriteLock.writeLock().unlock();
            }
        }
        return ResultGenerator.genSuccessResult(userVo);
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
        System.out.println(specCaptcha.toBase64());
        CaptchaVo captchaVo = new CaptchaVo();
        captchaVo.setVerifyToken(verifyToken);
        captchaVo.setData(specCaptcha.toBase64());
        // 将key和base64返回给前端
        return ResultGenerator.genSuccessResult(captchaVo);
    }
}
