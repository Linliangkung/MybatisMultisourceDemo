package com.jsako.advisor;

import java.lang.reflect.Method;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.After;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.jsako.annotations.TargetDataSource;
import com.jsako.datasource.DatasourceHelper;
import com.jsako.pointcut.TargetDatasourcePointcut;

@Component
public class TargetDatasourceAdvisor implements PointcutAdvisor ,Ordered{
	
	private static class TargetDatasourceInterceptor implements MethodInterceptor{
		
		private static ThreadLocal<Integer> numThreadLocal = new ThreadLocal<Integer>();
		
		public void before(Method method,Class<?> targetClass){
			TargetDataSource classTargetDataSource =  targetClass.getAnnotation(TargetDataSource.class);
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
		
		public void after(){
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
		
		@Override
		public Object invoke(MethodInvocation invocation) throws Throwable {
			before(invocation.getMethod(),invocation.getThis().getClass());
			Object result = invocation.proceed();
			after();
			return result;
		}
	}
	
	@Autowired
	private TargetDatasourcePointcut targetDatasourcePointcut;
	private Advice advice;
	
	public TargetDatasourceAdvisor(){
		advice=new TargetDatasourceInterceptor();
	}
	
	@Override
	public Advice getAdvice() {
		return advice;
	}

	@Override
	public boolean isPerInstance() {
		return true;
	}

	@Override
	public Pointcut getPointcut() {
		return targetDatasourcePointcut;
	}

	@Override
	public int getOrder() {
		return 0;
	}

}
