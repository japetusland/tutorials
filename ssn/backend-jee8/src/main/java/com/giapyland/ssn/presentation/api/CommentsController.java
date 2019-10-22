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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.giapyland.ssn.business.PostsManager;
import com.giapyland.ssn.business.model.CommentDto;
import com.giapyland.ssn.presentation.api.model.Comment;
import com.giapyland.ssn.presentation.api.util.LocaleUtils;
import com.giapyland.ssn.presentation.api.util.SsnSecurityContext;
import com.giapyland.ssn.utils.StringUtils;

@Named
@RequestScoped
@Path("comments")
public class CommentsController {

	@Inject
	private SsnSecurityContext securityContext;

	@Inject
	private PostsManager postsManager;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addComment(Comment comment, @QueryParam("lang") String lang) {
		if (!securityContext.isUserAuthenticated())
			throw new WebApplicationException(LocaleUtils.getErrorMessage("com.giapyland.ssn.unauthorized", lang),
					HttpServletResponse.SC_UNAUTHORIZED);
		
		if (comment == null || StringUtils.IsNullOrWhiteSpace(comment.getContent()) || comment.getPostId() < 0)
			return Response.ok().build();
		
		if (!postsManager.addComment(comment.getPostId(), securityContext.getUserPrincipal(), comment.getContent()))
			throw new WebApplicationException(LocaleUtils.getErrorMessage("com.giapyland.ssn.commentNotAdded", lang),
					HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return Response.ok().build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public Response getCommentsByPost(@PathParam("id") long id, @QueryParam("lang") String lang) {
		if (!securityContext.isUserAuthenticated())
			throw new WebApplicationException(LocaleUtils.getErrorMessage("com.giapyland.ssn.unauthorized", lang),
					HttpServletResponse.SC_UNAUTHORIZED);
		List<CommentDto> comments = postsManager.getComments(id);
		List<Comment> result = new ArrayList<Comment>();
		for (CommentDto commentDto : comments)
			result.add(new Comment(commentDto));
		return Response.ok(result).build();
	}
}
