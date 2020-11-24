package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.XcUserMapper;
import com.company.project.model.XcUser;
import com.company.project.service.XcUserService;
import com.company.project.utils.*;
import com.company.project.vo.LoginVo;
import com.company.project.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
}
