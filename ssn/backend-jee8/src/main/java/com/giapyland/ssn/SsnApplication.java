package com.giapyland.ssn;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@CustomFormAuthenticationMechanismDefinition(
	loginToContinue = @LoginToContinue(
		loginPage = "/login.xhtml",
		useForwardToLogin = false
	)
)

@Singleton
@Startup
public class SsnApplication {

	public static final Logger LOGGER = LogManager.getLogger(SsnApplication.class);
	public static final String ROLE_USER = "USER";
}
