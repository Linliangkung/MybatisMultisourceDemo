package com.jsako.pointcut;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

/**
 * 
 * @author LLJ 2018年6月29日
 */
public class ClassAndMethodAnnotationMatchPointcut implements Pointcut {
	
	private Class<? extends Annotation> targetAnnotation;
	
	private class ClassAndMethodAnnotationMatchMethodMatcher implements MethodMatcher{
		
		@Override
		public boolean matches(Method method, Class<?> targetClass,
				Object... args) {
			if(targetAnnotation==null){
				throw new IllegalStateException("field targetAnnotation should not be null!");
			}
			if (!Object.class.equals(method.getDeclaringClass()) 
					&& (method.isAnnotationPresent(targetAnnotation)  || targetClass
							.isAnnotationPresent(targetAnnotation))) {
				return true;
			}
			return false;
		}

		@Override
		public boolean matches(Method method, Class<?> targetClass) {
			return this.matches(method, targetClass, new Object[]{});
		}

		@Override
		public boolean isRuntime() {
			return false;
		}
		
		
	};
	
	private MethodMatcher methodMatcher;
	
	public ClassAndMethodAnnotationMatchPointcut(){
		this.methodMatcher=new ClassAndMethodAnnotationMatchMethodMatcher();
	}
	
	@Override
	public ClassFilter getClassFilter() {
		return ClassFilter.TRUE;
	}

	@Override
	public MethodMatcher getMethodMatcher() {
		return methodMatcher;
	}

	@SuppressWarnings("unchecked")
	public void setTargetAnnotationClassname(String targetAnnotationClassname) throws ClassNotFoundException {
		this.targetAnnotation=(Class<? extends Annotation>) Class.forName(targetAnnotationClassname);
	}

}
