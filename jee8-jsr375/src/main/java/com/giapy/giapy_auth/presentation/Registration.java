package com.giapy.giapy_auth.presentation;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import com.giapy.giapy_auth.business.AuthenticationManager;
import com.giapy.giapy_auth.presentation.validators.ValidationUtils;

@Named
@RequestScoped
public class Registration {

	@Inject
	private AuthenticationManager authenticationManager; 
	
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
		System.out.println("email: " + this.email);
		System.out.println("password: " + this.password);
		if (authenticationManager.createUser(email, password))
			return "/index?faces-redirect=true";
		ValidationUtils.raise("giapy.giapy-auth.cannotRegisterUser");
		return null;
	}
	
	public void validateEmails(FacesContext context, UIComponent component, Object value) 
			throws ValidatorException {
		String email = (String) value;
		if (email==null || email=="")
			ValidationUtils.raise("giapy.giapy-auth.invalidEmail");
		UIInput confirmEmailComponent = (UIInput)component.findComponent("confirmEmail");
		String confirmEmail = confirmEmailComponent.getSubmittedValue().toString();
		if (!email.equals(confirmEmail))
			ValidationUtils.raise("giapy.giapy-auth.emailsDontMatch");
		if (authenticationManager.accountExists(email))
			ValidationUtils.raise("giapy.giapy-auth.accountAlreadyExists");
	}

	public void validatePasswords(FacesContext context, UIComponent component, Object value) 
			throws ValidatorException {
		String password = (String)value;
		if (password==null || password=="") 
			ValidationUtils.raise("giapy.giapy-auth.invalidPassword");
		UIInput confirmPasswordComponent = (UIInput)component.findComponent("confirmPassword");
		String confirmPassword = confirmPasswordComponent.getSubmittedValue().toString();
		if (!password.equals(confirmPassword))
			ValidationUtils.raise("giapy.giapy-auth.passwordsDontMatch");
	}

}
