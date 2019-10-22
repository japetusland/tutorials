package com.giapyland.ssn.presentation.validators;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import com.giapyland.ssn.SsnApplication;

public class ValidationUtils {

	public static String getErrorMessage(String key) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("com.giapyland.ssn.errors",
					context.getViewRoot().getLocale());
			return bundle.getString(key);
		} catch (Exception e) {
			SsnApplication.LOGGER.error(e.getMessage());
		}
		return "";
	}

	public static void raise(String key) throws ValidatorException {
		FacesMessage msg = new FacesMessage(getErrorMessage(key));
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		throw new ValidatorException(msg);
	}
}
