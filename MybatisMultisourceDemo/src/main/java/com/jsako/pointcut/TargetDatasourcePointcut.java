package com.jsako.pointcut;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.stereotype.Component;

import com.jsako.annotations.TargetDataSource;

@Component
public class TargetDatasourcePointcut implements Pointcut {
	
	private ClassFilter classFilter=ClassFilter.TRUE;
	private MethodMatcher methodMatcher=new MethodMatcher() {
			
		
		@Override
		public boolean matches(Method method, Class<?> targetClass, Object... args) {
			if(!Object.class.equals(method.getDeclaringClass())&&(method.getAnnotation(TargetDataSource.class)!=null||targetClass.getAnnotation(TargetDataSource.class)!=null)){
				return true;
			}
			return false;
		}
		
		@Override
		public boolean matches(Method method, Class<?> targetClass) {
			return this.matches(method, targetClass,null);
		}
		
		@Override
		public boolean isRuntime() {
			return false;
		}
	};
	
	@Override
	public ClassFilter getClassFilter() {
		return classFilter;
	}

	@Override
	public MethodMatcher getMethodMatcher() {
		return methodMatcher;
	}

}
