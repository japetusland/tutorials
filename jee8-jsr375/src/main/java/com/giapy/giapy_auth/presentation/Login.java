package com.giapy.giapy_auth.presentation;

import static javax.faces.annotation.FacesConfig.Version.JSF_2_3;

import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.giapy.giapy_auth.application.ApplicationConfig;
import com.giapy.giapy_auth.presentation.validators.ValidationUtils;

@FacesConfig(version = JSF_2_3)
@Named
@RequestScoped
public class Login {

	@Inject
	private SecurityContext securityContext;

	@Inject
	private FacesContext facesContext;

	@Inject
	ExternalContext externalContext;
	
	@Inject @ManagedProperty("#{param.new}")
	private boolean isNew;	

	private String email;

	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void login() {
        Credential credential = new UsernamePasswordCredential(email, new Password(password));
        HttpServletRequest request = (HttpServletRequest)facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse)facesContext.getExternalContext().getResponse();

        AuthenticationStatus status = securityContext.authenticate(request, response, AuthenticationParameters.withParams().newAuthentication(isNew).credential(credential));

        if (status.equals(AuthenticationStatus.SEND_CONTINUE)) {
            facesContext.responseComplete();
        } else if (status.equals(AuthenticationStatus.SEND_FAILURE)) {
        	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ValidationUtils.getErrorMessage("giapy.giapy-auth.loginError"), null));
        } else if (status.equals(AuthenticationStatus.SUCCESS)) {
        	try {
        		externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
        	} catch (Exception e) {
        		ApplicationConfig.LOGGER.error(e.getMessage());
            	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ValidationUtils.getErrorMessage("giapy.giapy-auth.loginSucceded"), null));
        	}
        }
	}

}
