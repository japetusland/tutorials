package com.giapyland.ssn.presentation.api;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.giapyland.ssn.business.PostsManager;
import com.giapyland.ssn.business.model.PostDto;
import com.giapyland.ssn.presentation.api.model.Post;
import com.giapyland.ssn.presentation.api.util.LocaleUtils;
import com.giapyland.ssn.presentation.api.util.SsnSecurityContext;
import com.giapyland.ssn.utils.StringUtils;

@Named
@RequestScoped
@Path("posts")
public class PostsController {

	@Inject
	private SsnSecurityContext securityContext;

	@Inject
	private PostsManager postsManager;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPost(Post post, @QueryParam("lang") String lang) {
		if (!securityContext.isUserAuthenticated())
			throw new WebApplicationException(LocaleUtils.getErrorMessage("com.giapyland.ssn.unauthorized", lang),
					HttpServletResponse.SC_UNAUTHORIZED);
		
		if (post == null || StringUtils.IsNullOrWhiteSpace(post.getContent()))
			return Response.ok().build();
			
		if (!postsManager.addPost(securityContext.getUserPrincipal(), post.getContent()))
			throw new WebApplicationException(LocaleUtils.getErrorMessage("com.giapyland.ssn.postNotAdded", lang),
					HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return Response.ok().build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response editPost(Post post, @QueryParam("lang") String lang) {
		if (!securityContext.isUserAuthenticated())
			throw new WebApplicationException(LocaleUtils.getErrorMessage("com.giapyland.ssn.unauthorized", lang),
					HttpServletResponse.SC_UNAUTHORIZED);
		if (!postsManager.updatePost(post.getId(), post.getContent()))
			throw new WebApplicationException(LocaleUtils.getErrorMessage("com.giapyland.ssn.postNotModified", lang),
					HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return Response.ok().build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{email}/")
	public Response getPostsByUser(@PathParam("email") String email, @QueryParam("lang") String lang) {
		if (!securityContext.isUserAuthenticated())
			throw new WebApplicationException(LocaleUtils.getErrorMessage("com.giapyland.ssn.unauthorized", lang),
					HttpServletResponse.SC_UNAUTHORIZED);
		List<PostDto> posts = postsManager.getPosts(email);
		List<Post> result = new ArrayList<Post>();
		for (PostDto postDto : posts)
			result.add(new Post(postDto));
		return Response.ok(result).build();
	}
}
