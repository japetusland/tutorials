package com.giapyland.ssn.presentation.validators;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.giapyland.ssn.passwordValidator")
public class PasswordValidator implements Validator<String> {

	@Override
	public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
		if (value == null || value.length() < 5)
			ValidationUtils.raise("com.giapyland.ssn.invalidPassword");
	}

}
