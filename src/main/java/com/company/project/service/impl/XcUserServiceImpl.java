package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.dao.XcUserMapper;
import com.company.project.model.XcUser;
import com.company.project.service.XcUserService;
import com.company.project.core.AbstractService;
import com.company.project.utils.Logger;
import com.company.project.vo.LoginVo;
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

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public Result login(LoginVo vo) {

        readWriteLock.writeLock().lock();

        try {

            XcUser xcUser = tUserMapper.findUserByPhone(vo.getPhone());

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

        return null;
    }
}
