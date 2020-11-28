package com.zhenwen.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhenwen
 * @date 2020/10/13
 */
@Aspect
public class AspectConfig {

    /**
     * 使用指定类初始化日志对象,在日志输出的时候，可以打印出日志信息所在的类：
     */
    private final static Logger logger = LoggerFactory.getLogger(AspectConfig.class);

    /**
     * 定义切点
     */
    @Pointcut("execution(* com.zhenwen.service.impl.*.*(..))")
    public void controllerLog() {
    }

    /**
     * 非入侵式设计
     */
    @Before(value = "controllerLog()")
    public void doBeforeController(JoinPoint joinPoint) {
//        String name = joinPoint.getSignature().getName();
//        System.out.println(name);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
        String format = sdf.format(new Date());
        String ip = request.getRemoteAddr();
        String url = request.getRequestURI();
        String methodType = request.getMethod();

        //打印日志
        logger.debug("-----------------------------------------------------------------------------------------------------");
        logger.debug("时间 = {}", format);
        logger.debug("访问url = {}", url);
        logger.debug("请求方法类型 = {}", methodType);
        logger.debug("来源ip地址 = {}", ip);
        logger.debug("调用方法 = {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.debug("-----------------------------------------------------------------------------------------------------");
    }

    @Around(value = "controllerLog()")
    public Object doAfterController(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        long end = System.currentTimeMillis();
        long time = end - start;
        logger.debug("执行时间 = {}", time + "ms");
        logger.info("执行时间 = {}", time + "ms");
        return object;
    }

}
