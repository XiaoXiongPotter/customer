package com.dognessnetwork.customer.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ch.qos.logback.classic.Logger;

@Aspect
@Component
public class RequestAspect {
        private Logger logger= (Logger) LoggerFactory.getLogger(RequestAspect.class);
        
        /**
         * Pointcut定义切点
         * public修饰符的   返回值任意  com.cy.controller包下面的任意类的任意方法任意参数
         */
        @Pointcut("execution(public * com.dognessnetwork.customer.controller.*.*(..))")
        public void log(){
            
        }
        
        @Before("log()")
        public void doBefore(JoinPoint joinPoint){
            logger.info("方法执行前...");
            ServletRequestAttributes sra =  (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = sra.getRequest();
            logger.info("url: " + request.getRequestURI());
            logger.info("ip: " + request.getRemoteHost());
            logger.info("method: "+request.getMethod());
            logger.info("class.method: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()); 
            logger.info("args: "+joinPoint.getArgs());
            //Student student = (Student) joinPoint.getArgs()[0];
            //logger.info(student.toString());
        }
        
        @After("log()")
        public void doAfter(JoinPoint joinPoint){
            logger.info("方法执行后...");
        }
        
        @AfterReturning(returning="result", pointcut="log()")
        public void doAfterReturnint(Object result){
            logger.info("方法返回值：" + result);
        }
}
