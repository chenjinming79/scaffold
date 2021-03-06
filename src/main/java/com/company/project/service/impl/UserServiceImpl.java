package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.UserMapper;
import com.company.project.model.User;
import com.company.project.service.SysMenuService;
import com.company.project.service.UserService;
import com.company.project.core.AbstractService;
import com.company.project.utils.*;
import com.company.project.vo.CaptchaVo;
import com.company.project.vo.LoginVo;
import com.company.project.vo.SysUserVo;
import com.company.project.vo.VerfiyCodeVo;
import com.wf.captcha.GifCaptcha;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
* Created by CodeGenerator on 2021/04/16.
*/
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private MagConfig magConfig;

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public Result logout(Long userId) {
        SysUserVo sysUserVo = null;
        String token=(String)redisService.get(userId+"USERID");
        try {
            sysUserVo = (SysUserVo)redisService.get(Constants.REDIS_KEY_LOGIN + token);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("redis异常");
        }

        redisService.delete(userId+"USERID");

        if (sysUserVo != null){
            redisService.delete(Constants.REDIS_KEY_LOGIN + token);

            return ResultGenerator.genSuccessResult();
        }

        return ResultGenerator.genFailResult(ResultCode.NOT_LOGIN_EXCEPTION,"用户未登录,请重新登录");
    }

    @Override
    public Result login(LoginVo vo) {

        //读写锁
        //只允许一个线程写入（其他线程既不能写入也不能读取）
        //没有写入时，多个线程允许同时读（提高性能）
        readWriteLock.writeLock().lock();

        //声明存储对象
        SysUserVo sysUserVo = new SysUserVo();

        User user = new User();

        //根据用户名查询
        user = userMapper.findUserByUserName(vo.getUserName(),null);

        if (null == user){
            return ResultGenerator.genFailResult(ResultCode.USER_NOT_EXIST,"用户信息不存在[账号可能被停用或删除]");
        }

        if(!vo.getPassword().equals(user.getPassword())){
            return ResultGenerator.genFailResult(ResultCode.PASSWORD_ERROR,"密码输入错误，请重新输入");
        }

        //获取对应的模块
        List<Object> sysMenuList = new ArrayList<Object>();
        if (null != user.getRole()){
            sysMenuList = sysMenuService.selectMenuByRoleId(user.getRole());
        }

        //创建token
        String token= (String) redisService.get(user.getId() + "USERID");

        Boolean loginFlag = false;

        if(StringUtils.isNotBlank(token)){
            //说明已登陆，或直接断网
            redisService.delete(Constants.REDIS_KEY_LOGIN + token);
            redisService.delete(user.getId()+"USERID");
        }else{
            //true首次
            loginFlag=true;
        }
        token = TokenUtil.getToken();

        try {
            //存入用户相关信息
            sysUserVo.setUserId(user.getId());
            sysUserVo.setPhone(user.getPhone());
            sysUserVo.setEmail(user.getEmail());
            sysUserVo.setToken(token);
            //设置该token的过期时间
            sysUserVo.setTokenExpireTime(System.currentTimeMillis() + magConfig.getExpireTime());
            sysUserVo.setUserName(user.getUserName());
            sysUserVo.setSysMenuList(sysMenuList);
            sysUserVo.setRoleId(user.getRole().toString());
            //redisService.put(Constant.REDIS_KEY_LOGIN, token, new RedisModel(su.getId(), System.currentTimeMillis() + magConfig.getExpireTime()), magConfig.getExpireTime());
            redisService.setWithExpire(Constants.REDIS_KEY_LOGIN + token, sysUserVo , 2505600000L);
            redisService.set(user.getId()+"USERID",token);
        }catch (Exception e){
            e.printStackTrace();
            Logger.info(this,"登录token存入redis产生异常："+e.getMessage());
            throw new RuntimeException("存入redis异常");
        }finally {
            if(readWriteLock.isWriteLocked()){
                readWriteLock.writeLock().unlock();
            }
        }
        return ResultGenerator.genSuccessResult(sysUserVo);
    }

    @Override
    public Result captcha() {
        GifCaptcha specCaptcha = new GifCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        System.out.print("登录验证码" + verCode);
        String verifyToken = TokenUtil.getToken();
        // 存入redis并设置过期时间为30秒
        redisService.setWithExpire(Constants.REDIS_KEY_VERFIY + verifyToken, new VerfiyCodeVo(verCode,System.currentTimeMillis() + Constants.verifyCodeForTempValidTime)  , Constants.verifyCodeForTempValidTime);
        CaptchaVo captchaVo = new CaptchaVo();
        captchaVo.setVerifyToken(verifyToken);
        captchaVo.setData(specCaptcha.toBase64());
        // 将key和base64返回给前端
        return ResultGenerator.genSuccessResult(captchaVo);
    }

    @Override
    public Result updateUser(User user) {

        //根据用户名查询是否存在
        User newUser = userMapper.findUserByUserName(user.getUserName(),user.getId());

        //用户名不可重复
        if (null != newUser){
            return ResultGenerator.genFailResult(ResultCode.USER_ALREADY_EXIST,"用户名已存在，请登录");
        }

        user.setUpdatedAt(new Date());
        update(user);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(user);
        return result;
    }

    @Override
    public Result add(User user) {

        User newUser = userMapper.findUserByUserName(user.getUserName(),null);

        if (null != newUser){
            return ResultGenerator.genFailResult(ResultCode.USER_ALREADY_EXIST,"用户名已存在，请登录");
        }

        user.setCreatedAt(new Date());
        user.setIsDelete(false);
        save(user);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(user);
        return result;
    }

}
