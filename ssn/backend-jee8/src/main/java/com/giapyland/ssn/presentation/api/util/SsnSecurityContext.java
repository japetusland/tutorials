package com.giapyland.ssn.presentation.api.util;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import com.giapyland.ssn.SsnApplication;

@Named
@Singleton
public class SsnSecurityContext {

	@Context
	SecurityContext securityContext;

	@GET
	public String getUserPrincipal() {
		return securityContext.getUserPrincipal().getName();
	}

	public boolean isUserAuthenticated() {
		return securityContext.isUserInRole(SsnApplication.ROLE_USER);
	}

}
