package com.giapy.giapy_auth.application;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
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



@Named
@ApplicationScoped
public class ApplicationConfig {

	public static final Logger LOGGER = LogManager.getLogger(ApplicationConfig.class);
	public static final String ADMIN = "ADMIN";
	public static final String USER = "USER";

}
