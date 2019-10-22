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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.giapyland.ssn.business.PostsManager;
import com.giapyland.ssn.business.model.PostDto;
import com.giapyland.ssn.presentation.api.model.Post;
import com.giapyland.ssn.presentation.api.utils.LocaleUtils;
import com.giapyland.ssn.utils.StringUtils;

@RestController
@RequestMapping("/api/posts/")
public class PostsController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private PostsManager postsManager;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addPost(Principal principal, @RequestBody Post post, @RequestParam("lang") String lang) {
		
		Locale clientLocale = LocaleUtils.GetLocale(lang);
		
		if (StringUtils.IsNullOrWhiteSpace(post.getContent()))
			return ResponseEntity.ok().build();
		
		if (!postsManager.addPost(principal.getName(), post.getContent()))
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					messageSource.getMessage("com.giapyland.ssn.postNoAdded", null, clientLocale));
		
		return ResponseEntity.ok().build();
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> editPost(@RequestBody Post post, @RequestParam("lang") String lang) {
		
		Locale clientLocale = LocaleUtils.GetLocale(lang);
		
		if (!postsManager.updatePost(post.getId(), post.getContent()))
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					messageSource.getMessage("com.giapyland.ssn.postNotUpdated", null, clientLocale));
		
		return ResponseEntity.ok().build();
	}

	@GetMapping(path = "{email}/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Post>> getPostsByUser(@PathVariable("email") String email) {
		
		List<PostDto> posts = postsManager.getPosts(email);
		
		List<Post> result = new ArrayList<Post>();
		
		for (PostDto postDto : posts)
			result.add(new Post(postDto));
		
		return ResponseEntity.ok(result);
	}
}
