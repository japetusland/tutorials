package com.giapyland.ssn.presentation.api;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.giapyland.ssn.business.SsnSecurityService;
import com.giapyland.ssn.presentation.api.util.LocaleUtils;
import com.giapyland.ssn.presentation.api.util.SsnSecurityContext;

@Named
@RequestScoped
@Path("friends")
public class FriendsController {

	@Inject
	private SsnSecurityContext securityContext;

	@Inject
	private SsnSecurityService securityService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers(@QueryParam("lang") String lang) {
		if (!securityContext.isUserAuthenticated())
			throw new WebApplicationException(LocaleUtils.getErrorMessage("com.giapyland.ssn.unauthorized", lang),
					HttpServletResponse.SC_UNAUTHORIZED);
		List<String> users = securityService.getUsers();
		users.remove(securityContext.getUserPrincipal());
		return Response.ok(users).build();
	}
}
