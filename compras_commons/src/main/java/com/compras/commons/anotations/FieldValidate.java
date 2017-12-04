package com.compras.commons.anotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={ ElementType.METHOD, ElementType.FIELD})

public @interface FieldValidate {
	
	boolean required() default true;
	int maximo() default (Integer.MAX_VALUE -1);
	String type() default "";
	boolean positivo() default true;
	

}
