package com.giapyland.ssn.presentation.api;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.giapyland.ssn.business.PostsManager;
import com.giapyland.ssn.business.model.CommentDto;
import com.giapyland.ssn.presentation.api.model.Comment;
import com.giapyland.ssn.presentation.api.utils.LocaleUtils;
import com.giapyland.ssn.utils.StringUtils;

@RestController
@RequestMapping("/api/comments/")
public class CommentsController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private PostsManager postsManager;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addComment(Principal principal, @RequestBody Comment comment,
			@RequestParam("lang") String lang) {

		Locale clientLocale = LocaleUtils.GetLocale(lang);

		if (StringUtils.IsNullOrWhiteSpace(comment.getContent()))
			return ResponseEntity.ok().build();

		if (!postsManager.addComment(comment.getPostId(), principal.getName(), comment.getContent()))
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					messageSource.getMessage("com.giapyland.ssn.commentNotAdded", null, clientLocale));

		return ResponseEntity.ok().build();
	}

	@GetMapping(path = "{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable("id") long id) {

		List<CommentDto> comments = postsManager.getComments(id);
		List<Comment> result = new ArrayList<Comment>();

		for (CommentDto commentDto : comments)
			result.add(new Comment(commentDto));

		return ResponseEntity.ok(result);
	}
}
