package com.company.project.aop;

import com.company.project.core.Result;
import com.company.project.model.XcOperatLog;
import com.company.project.model.XcWithdraw;
import com.company.project.service.XcOperatLogService;
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

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Component
@Aspect
public class OperatLogAspect {

    @Autowired
    private XcOperatLogService xcOperatLogService;

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

        xcOperatLogService.save(xcOperatLog);
    }

    /**
     * @Description 提现操作记录日志
     * @Date 14:47 2019/4/26
     * @Param
     * @return
     **/
    public void withdrawLog(OperatLog loggable,XcOperatLog operatLog,Object[] params,Result result){
        //删除操作
        if (loggable.function().equals(Constant.DELETE)){
            Long id = (Long) params[0];

            /*SaleType saleType = saleTypeMapper.selectByPrimaryKey(id);
            //如果等于空则该条数据不存在
            if (saleType==null){
                operatLog.setOperatContent("该条数据不存在[可能已被删除]");
            }else{
                //操作内容
                operatLog.setOperatContent(saleType.getTypeName());
            }*/
        }else{
            XcWithdraw xcWithdraw = (XcWithdraw) params[0];
            //新增操作
            if (loggable.function().equals(Constant.INSERT)){
                //操作内容
                operatLog.setOperatContent("提币操作");
            }
            //修改操作
            if (loggable.function().equals(Constant.UPDATE)){
                if (200 == result.getCode()){
                    operatLog.setOperatContent("修改提币操作 "+ " " +" 时"+Constant.SUCCESS);
                }else {
                    operatLog.setOperatContent("修改提币操作 "+ " " +" 时"+Constant.ERROR);
                }
            }
        }
    }

}
