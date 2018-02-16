package com.neev.moh.web.logConf;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;

@Aspect
@Component
public class LoggerAspect {
	@Around("execution(public * com.neev.moh.web..*(..))")
	public Object log(ProceedingJoinPoint pjp) throws Throwable {
		final MohLogger logger = MohLogFactory.getLoggerInstance(pjp.getTarget().getClass().getName());
		final Object[] args = pjp.getArgs();
		final String methodName = pjp.getSignature().getName();
		logger.log(MohLogger.INFO, "Enter: " + methodName + "()");
		if (!isUtilMethod(methodName)) {
			logger.log(MohLogger.INFO, methodName + "(): " + args);
		}
		final Object result = pjp.proceed();
		if (!isUtilMethod(methodName)) {
			logger.log(MohLogger.INFO, methodName + "(): result = " + result);
		}
		logger.log(MohLogger.INFO, "Exit: " + methodName + "()");
		return result;
	}

	private boolean isUtilMethod(String name) {
		return name.startsWith("get") || name.startsWith("set") || name.equals("toString") || name.equals("equals") || name.equals("hashCode");
	}
}
