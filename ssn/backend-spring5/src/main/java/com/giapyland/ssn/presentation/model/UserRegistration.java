package com.giapyland.ssn.presentation.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.giapyland.ssn.presentation.validators.Password;
import com.giapyland.ssn.presentation.validators.Matches;

@Matches.List({
	@Matches(first="email", second="confirmEmail", message="{Matches.email}"),
	@Matches(first="password", second="confirmPassword", message="{Matches.password}")
})
public class UserRegistration {
	
	@NotEmpty(message="{NotEmpty}") @Email(message="{Email}")
	private String email;
	
	@NotEmpty(message="{NotEmpty}") @Email(message="{Email}")
	private String confirmEmail;
	
	@NotEmpty(message="{NotEmpty}") @Password(message="{Password}")
	private String password;
	
	@NotEmpty(message="{NotEmpty}") @Password(message="{Password}")
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

}
