package com.giapyland.ssn.presentation.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.giapyland.ssn.emailValidator")
public class EmailValidator implements Validator<String> {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" + "(\\.[A-Za-z]{2,})$";

	private Pattern pattern;
	private Matcher matcher;

	public EmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	@Override
	public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {

		matcher = pattern.matcher(value);
		if (!matcher.matches())
			ValidationUtils.raise("com.giapyland.ssn.invalidEmail");
	}
}