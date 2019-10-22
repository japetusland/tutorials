package com.giapyland.ssn.presentation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.length() == 0 || value.length() >= 5;
	}

}
