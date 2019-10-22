package com.giapy.giapy_auth.presentation.validators;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("giapy.giapy-auth.passwordValidator")
public class PasswordValidator implements Validator<String> {

	@Override
	public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
		if (value==null || value.length()<5) 
			ValidationUtils.raise("giapy.giapy-auth.invalidPassword");
	}

}
