package com.giapyland.ssn.presentation.validators;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchesValidator implements ConstraintValidator<Matches, Object> {

	private String firstField;
	private String secondField;

	@Override
	public void initialize(Matches constraint) {
		firstField = constraint.first();
		secondField = constraint.second();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		try {
			Field firstProp = object.getClass().getDeclaredField(firstField);
			Field secondProp = object.getClass().getDeclaredField(secondField);

			firstProp.setAccessible(true);
			secondProp.setAccessible(true);

			Object firstValue = firstProp.get(object);
			Object secondValue = secondProp.get(object);

			return firstValue == null && secondValue == null || firstValue != null && firstValue.equals(secondValue);
		} catch (Exception ex) {
			throw new IllegalArgumentException("Cannot access fields: " + ex.toString());
		}
	}

}
