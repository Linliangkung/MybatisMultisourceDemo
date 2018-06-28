package com.jsako.annotations;

import java.lang.annotation.*;

/**
 * 指定数据源的注解
 * @author HZJ
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface TargetDataSource {

	String value();

}
