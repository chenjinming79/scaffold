package com.company.project.aop;

import com.company.project.core.Result;
import com.company.project.model.*;
import com.company.project.service.*;
import com.company.project.utils.Constant;
import com.company.project.utils.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

@Component
@Aspect
public class OperatLogAspect {

    @Autowired
    private XcOperatLogService xcOperatLogService;

    @Autowired
    private XcWithdrawService xcWithdrawService;

    @Autowired
    private XcSysConfigService xcSysConfigService;

    @Autowired
    private XcPayService xcPayService;

    @Autowired
    private XcPetParameterService xcPetParameterService;

    @Autowired
    private XcUserService xcUserService;

    //execution(* *(..)) 代表所有, @annotation(com.company.project.aop.OperatLog) 代表自定义注解
    @Pointcut(value = "execution(* *(..)) && @annotation(com.company.project.aop.OperatLog)")
    /*@Pointcut(value = "execution(* com.yt.ytotask.service.*.*(..))")*/ //具体哪里得操作
    public void controllerAspect(){

    }

    @AfterReturning(returning="result",pointcut = "controllerAspect()")
    public void before(JoinPoint point, Result result){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        //没有参数
        if (point.getArgs() == null){
            return;
        }

        // 获取参数
        Object[] params = point.getArgs();
        // 获取方法名
        String methodName = point.getSignature().getName();
        // 获取目标对象的类名
        Class<?> targetClass = point.getTarget().getClass();
        Method method = null;
        for (Method mt : targetClass.getMethods()) {
            if (methodName.equals(mt.getName())) {
                method = mt;
                break;
            }
        }

        //获取操作日志对象
        XcOperatLog xcOperatLog = new XcOperatLog();

        // 自定义注解
        OperatLog loggable = method.getAnnotation(OperatLog.class);

        if (loggable==null){
            Logger.info(this,"获取自定义注解失败");
        }

        //操作功能
        xcOperatLog.setOperatFunction(loggable.function());
        //操作模块
        xcOperatLog.setOperatModule(loggable.module());
        //操作状态
        if (200 == result.getCode()){
            xcOperatLog.setOperatState(Constant.SUCCESS);
        }else {
            xcOperatLog.setOperatState(Constant.ERROR);
        }

        //客户端IP
        xcOperatLog.setOperatIp(request.getRemoteAddr());

        //提现操作记录日志
        if (loggable.module().equals(Constant.WITHDRAW)){
            withdrawLog(loggable,xcOperatLog,params,result);
        }

        //系统配置操作记录日志
        if (loggable.module().equals(Constant.SYS_CONFIG)){
            sysConfigLog(loggable,xcOperatLog,params,result);
        }

        //充值记录操作记录日志
        if (loggable.module().equals(Constant.PAY)){
            payLog(loggable,xcOperatLog,params,result);
        }

        //星宠参数操作记录日志
        if (loggable.module().equals(Constant.PET_PARAMETER)){
            petParameterLog(loggable,xcOperatLog,params,result);
        }

        //会员操作记录日志
        if (loggable.module().equals(Constant.USER)){
            userLog(loggable,xcOperatLog,params,result);
        }

        xcOperatLogService.save(xcOperatLog);
    }

    /**
     * 星宠参数操作记录日志
     * @param loggable
     * @param xcOperatLog
     * @param params
     * @param result
     */
    private void userLog(OperatLog loggable, XcOperatLog xcOperatLog, Object[] params, Result result) {
        //删除操作
        if (loggable.function().equals(Constant.DELETE)){
            Long id = (Long) params[0];

            List<XcUser> xcUserList = xcUserService.findByModel(new XcUser(id));
            //如果等于空则该条数据不存在
            if (0 == xcUserList.size()){
                xcOperatLog.setOperatContent("该条数据不存在[可能已被删除]");
            }else{
                //操作内容
                xcOperatLog.setOperatContent("删除会员");
            }
        }else{
            XcUser xcUser = (XcUser) params[0];
            //新增操作
            if (Constant.INSERT.equals(loggable.function())){
                //操作内容
                xcOperatLog.setOperatContent("新增会员");
            }
            //修改操作
            if (Constant.UPDATE.equals(loggable.function())){
                if (200 == result.getCode()){
                    xcOperatLog.setOperatContent("修改会员操作 "+ xcUser.getId() +" 时"+Constant.SUCCESS);
                }else {
                    xcOperatLog.setOperatContent("修改会员操作 "+ xcUser.getId() +" 时"+Constant.ERROR);
                }
            }
        }
    }

    /**
     * 星宠参数操作记录日志
     * @param loggable
     * @param xcOperatLog
     * @param params
     * @param result
     */
    private void petParameterLog(OperatLog loggable, XcOperatLog xcOperatLog, Object[] params, Result result) {
        //删除操作
        if (loggable.function().equals(Constant.DELETE)){
            Long id = (Long) params[0];

            List<XcPetParameter> xcPetParameterList = xcPetParameterService.findByModel(new XcPetParameter(id));
            //如果等于空则该条数据不存在
            if (0 == xcPetParameterList.size()){
                xcOperatLog.setOperatContent("该条数据不存在[可能已被删除]");
            }else{
                //操作内容
                xcOperatLog.setOperatContent("删除星宠");
            }
        }else{
            XcPetParameter xcPetParameter = (XcPetParameter) params[0];
            //新增操作
            if (Constant.INSERT.equals(loggable.function())){
                //操作内容
                xcOperatLog.setOperatContent("新增星宠");
            }
            //修改操作
            if (Constant.UPDATE.equals(loggable.function())){
                if (200 == result.getCode()){
                    xcOperatLog.setOperatContent("修改星宠操作 "+ xcPetParameter.getId() +" 时"+Constant.SUCCESS);
                }else {
                    xcOperatLog.setOperatContent("修改星宠操作 "+ xcPetParameter.getId() +" 时"+Constant.ERROR);
                }
            }
        }
    }

    /**
     * 充值记录操作记录日志
     * @param loggable
     * @param xcOperatLog
     * @param params
     * @param result
     */
    private void payLog(OperatLog loggable, XcOperatLog xcOperatLog, Object[] params, Result result) {
        //删除操作
        if (loggable.function().equals(Constant.DELETE)){
            Long id = (Long) params[0];

            List<XcPay> xcPays = xcPayService.findByModel(new XcPay(id));
            //如果等于空则该条数据不存在
            if (0 == xcPays.size()){
                xcOperatLog.setOperatContent("该条数据不存在[可能已被删除]");
            }else{
                //操作内容
                xcOperatLog.setOperatContent("删除充值记录操作");
            }
        }else{
            XcPay xcPay = (XcPay) params[0];
            //新增操作
            if (Constant.INSERT.equals(loggable.function())){
                //操作内容
                xcOperatLog.setOperatContent("新增充值记录");
            }
            //修改操作
            if (Constant.UPDATE.equals(loggable.function())){
                if (200 == result.getCode()){
                    xcOperatLog.setOperatContent("修改充值记录操作 "+ xcPay.getId() +" 时"+Constant.SUCCESS);
                }else {
                    xcOperatLog.setOperatContent("修改充值记录操作 "+ xcPay.getId() +" 时"+Constant.ERROR);
                }
            }
        }
    }

    /**
     * 系统配置操作记录日志
     * @param loggable
     * @param xcOperatLog
     * @param params
     * @param result
     */
    private void sysConfigLog(OperatLog loggable, XcOperatLog xcOperatLog, Object[] params, Result result) {
        //删除操作
        if (loggable.function().equals(Constant.DELETE)){
            Long id = (Long) params[0];

            List<XcSysConfig> xcSysConfig = xcSysConfigService.findByModel(new XcSysConfig());
            //如果等于空则该条数据不存在
            if (0 == xcSysConfig.size()){
                xcOperatLog.setOperatContent("该条数据不存在[可能已被删除]");
            }else{
                //操作内容
                xcOperatLog.setOperatContent("删除系统配置操作");
            }
        }else{
            XcSysConfig xcSysConfig = (XcSysConfig) params[0];
            //新增操作
            if (Constant.INSERT.equals(loggable.function())){
                //操作内容
                xcOperatLog.setOperatContent("新增系统配置");
            }
            //修改操作
            if (Constant.UPDATE.equals(loggable.function())){
                if (200 == result.getCode()){
                    xcOperatLog.setOperatContent("修改系统配置操作 "+ xcSysConfig.getId() +" 时"+Constant.SUCCESS);
                }else {
                    xcOperatLog.setOperatContent("修改系统配置操作 "+ xcSysConfig.getId() +" 时"+Constant.ERROR);
                }
            }
        }
    }

    /**
     * 提现操作记录日志
     * @param loggable
     * @param operatLog
     * @param params
     * @param result
     */
    public void withdrawLog(OperatLog loggable,XcOperatLog operatLog,Object[] params,Result result){
        //删除操作
        if (Constant.DELETE.equals(loggable.function())){
            Long id = (Long) params[0];

            List<XcWithdraw> xcWithdraw = xcWithdrawService.findByModel(new XcWithdraw(id));
            //如果等于空则该条数据不存在
            if (0 == xcWithdraw.size()){
                operatLog.setOperatContent("该条数据不存在[可能已被删除]");
            }else{
                //操作内容
                operatLog.setOperatContent("删除提币操作");
            }
        }else{
            XcWithdraw xcWithdraw = (XcWithdraw) params[0];
            //新增操作
            if (Constant.INSERT.equals(loggable.function())){
                //操作内容
                operatLog.setOperatContent("新增提币操作");
            }
            //修改操作
            if (Constant.UPDATE.equals(loggable.function())){
                if (200 == result.getCode()){
                    operatLog.setOperatContent("修改提币操作 "+ xcWithdraw.getId() +" 时"+Constant.SUCCESS);
                }else {
                    operatLog.setOperatContent("修改提币操作 "+ xcWithdraw.getId() +" 时"+Constant.ERROR);
                }
            }
        }
    }

}
