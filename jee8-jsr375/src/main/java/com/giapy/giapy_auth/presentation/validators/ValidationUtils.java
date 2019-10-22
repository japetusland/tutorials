package com.giapy.giapy_auth.presentation.validators;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

public class ValidationUtils {

	public static String getErrorMessage(String key) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("com.giapy.giapy-auth.errors", context.getViewRoot().getLocale());
			return bundle.getString(key);
		} catch (Exception e) {
			System.out.println("EXCEPTION: " + e);
		}
		return "";
	}
	
	public static void raise(String key) throws ValidatorException  {
		FacesMessage msg = new FacesMessage(getErrorMessage(key));
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		throw new ValidatorException(msg);	
	}

}
