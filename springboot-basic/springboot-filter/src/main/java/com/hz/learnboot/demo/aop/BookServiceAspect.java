package com.hz.learnboot.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * AOP
 */
@Aspect
@Component
public class BookServiceAspect {

	private static final Logger logger = LoggerFactory.getLogger(BookServiceAspect.class);

	// 后置增强
	@AfterReturning("execution(* com.hz.learnboot.demo..BookService*.*(..))")
	public void logServiceAccess(JoinPoint joinPoint) {
		logger.info("执行 Spring AOP...");
		logger.info("Completed: " + joinPoint);
	}

}
