package com.aop.aspect;

import com.aop.annotation.LogAnno;
import com.mapper.LogInfoMapper;
import com.model.generate.LogInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
* @Description: 切面日志配置
* @Param:  
* @return:  
* @Author: endure
* @Date: 2020/1/13 
*/
@Aspect
@Component
public class LogAspect {
    private final static Logger log = org.slf4j.LoggerFactory.getLogger(LogAspect.class);

    @Resource
    private LogInfoMapper logInfoMapper;
    
    //表示匹配带有自定义注解的方法
    @Pointcut("@annotation(com.aop.annotation.LogAnno)")
    public void pointcut() {}
    
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result =null;
        try {
            insertLog(point);
            log.info("我在目标方法之前执行！");
            result = point.proceed();
            log.info("我在目标方法之后执行！");
        } catch (Throwable e) {
            log.info("系统错误！"+e);
        }
        return result;
    }
    
    private void insertLog(ProceedingJoinPoint point ) {
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();
        LogInfo logInfo = new LogInfo();
        LogAnno userAction = method.getAnnotation(LogAnno.class);
        if (userAction != null) {
            // 注解上的描述
            logInfo.setActionParam(userAction.operateType());
        }
        // 请求的类名
        String className = point.getTarget().getClass().getName();
        // 请求的方法名
        String methodName = signature.getName();
        // 请求的方法参数值
        String args = Arrays.toString(point.getArgs());
        logInfo.setCreatedate(new java.sql.Timestamp(new Date().getTime()));
        log.info("类名:{},方法名:{},参数：{}", className, methodName, args);
        logInfoMapper.insertSelective(logInfo);
    }
}