package com.giapyland.ssn.presentation.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { MatchesValidator.class })
public @interface Matches {

	String message() default "the values are not the same";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String first();

	String second();

	@Target({ ElementType.FIELD, ElementType.TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		Matches[] value();
	}
}
