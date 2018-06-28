package com.jsako.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.jsako.annotations.TargetDataSource;
import com.jsako.datasource.DatasourceHelper;

@Component
public class TargetDatasourceAspect {
	
	private static ThreadLocal<Integer> numThreadLocal = new ThreadLocal<Integer>();
	
	public void dataSourcePointcut(){
	}
	
	public void beforeExcute(JoinPoint joinPoint) {
		
		MethodSignature methdSignature = (MethodSignature) joinPoint.getSignature();
		
		Method method = methdSignature.getMethod();
		Class<?> targetClass = methdSignature.getDeclaringType();
		
		TargetDataSource classTargetDataSource = targetClass.getAnnotation(TargetDataSource.class);
		TargetDataSource methodTargetDataSource = method.getAnnotation(TargetDataSource.class);
		
		TargetDataSource ds = methodTargetDataSource != null ? methodTargetDataSource : classTargetDataSource;
		
		if(ds != null) {
			String dsName = ds.value();
			Integer num = numThreadLocal.get();
			if(num == null) {
				num = 1;
			} else {
				num++;
			}
			numThreadLocal.set(num);
			
			String dataSourceVal = DatasourceHelper.getDatasource();
			if(dataSourceVal == null) {
				DatasourceHelper.setDataSource(dsName);
			}
		}
	}
	
	public void afterExcute() {
		Integer num = numThreadLocal.get();
		if(num == null) {
			num = 0;
		}
		num--;
		numThreadLocal.set(num);
		if(num <= 0) {
			DatasourceHelper.remove();
			numThreadLocal.remove();
		}
	}
	
	
}
