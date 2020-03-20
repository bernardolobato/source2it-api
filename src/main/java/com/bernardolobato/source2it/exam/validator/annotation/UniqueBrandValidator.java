 package com.bernardolobato.source2it.exam.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import javax.validation.Constraint;
import javax.validation.Payload;
import com.bernardolobato.source2it.exam.validator.UniqueBrandValidatorImpl;

@Documented
@Constraint(validatedBy = UniqueBrandValidatorImpl.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface UniqueBrandValidator {
	   String message() default "Já existe uma marca com o nome informado";
       Class<?>[] groups() default {};
	   Class<? extends Payload>[] payload() default {};
	
}