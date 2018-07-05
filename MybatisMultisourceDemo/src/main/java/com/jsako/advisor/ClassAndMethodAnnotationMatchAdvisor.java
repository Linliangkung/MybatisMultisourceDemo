package com.jsako.advisor;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractGenericPointcutAdvisor;

import com.jsako.pointcut.ClassAndMethodAnnotationMatchPointcut;


/**
 * 
 * @author LLJ
 * 2018年6月29日
 *	用于判断spring容器中的bean中的类上面的注解或者方法上的注解是否包含指定的注解，如果包含则生成为该bean创建代理对象
 */
public class ClassAndMethodAnnotationMatchAdvisor extends AbstractGenericPointcutAdvisor {
	
	private final ClassAndMethodAnnotationMatchPointcut pointcut=new ClassAndMethodAnnotationMatchPointcut();
	
	@Override
	public Pointcut getPointcut() {
		return pointcut;
	}

	public void setTargetAnnotationClassname(String targetAnnotationClassname) throws ClassNotFoundException {
		pointcut.setTargetAnnotationClassname(targetAnnotationClassname);
	}
	
}
