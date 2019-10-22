package com.giapyland.ssn.presentation.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface Password {

	String message() default "Invalid password";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
