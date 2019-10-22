package com.giapyland.ssn.presentation;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import com.giapyland.ssn.business.SsnSecurityService;
import com.giapyland.ssn.presentation.validators.ValidationUtils;

@Named
@RequestScoped
public class Registration {

	@Inject
	private SsnSecurityService authenticationManager;

	@Inject
	private FacesContext facesContext;

	private String email;

	private String confirmEmail;

	private String password;

	private String confirmPassword;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmEmail() {
		return confirmEmail;
	}

	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String register() throws ValidatorException {
		SsnSecurityService.UserCreationResult registrationResult = authenticationManager.createUser(email, password);

		if (registrationResult.equals(SsnSecurityService.UserCreationResult.USER_ALREADY_EXISTS)) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					ValidationUtils.getErrorMessage("com.giapyland.ssn.userAlreadyExists"), null));
			return null;
		}

		if (registrationResult.equals(SsnSecurityService.UserCreationResult.SUCCESS))
			return "/login.xhtml?faces-redirect=true";

		if (registrationResult.equals(SsnSecurityService.UserCreationResult.FAILURE))
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				ValidationUtils.getErrorMessage("com.giapyland.ssn.serverError"), null));
		return null;
	}

	public void validateEmails(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String email = (String) value;
		if (email == null || email == "")
			ValidationUtils.raise("com.giapyland.ssn.invalidEmail");
		UIInput confirmEmailComponent = (UIInput) component.findComponent("confirmEmail");
		String confirmEmail = confirmEmailComponent.getSubmittedValue().toString();
		if (!email.equals(confirmEmail))
			ValidationUtils.raise("com.giapyland.ssn.emailDontMatch");
	}

	public void validatePasswords(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String password = (String) value;
		if (password == null || password == "")
			ValidationUtils.raise("com.giapyland.ssn.invalidPassword");
		UIInput confirmPasswordComponent = (UIInput) component.findComponent("confirmPassword");
		String confirmPassword = confirmPasswordComponent.getSubmittedValue().toString();
		if (!password.equals(confirmPassword))
			ValidationUtils.raise("com.giapyland.ssn.passwordDontMatch");
	}

}
