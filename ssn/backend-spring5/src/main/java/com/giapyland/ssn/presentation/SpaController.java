package com.giapyland.ssn.presentation;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.giapyland.ssn.SsnApplication;
import com.giapyland.ssn.business.PostsManager;
import com.giapyland.ssn.business.SsnSecurityService;
import com.giapyland.ssn.business.model.PostDto;
import com.giapyland.ssn.presentation.api.model.Post;
import com.giapyland.ssn.presentation.model.SpaInitialData;

@Controller
public class SpaController {

	@Autowired
	private SsnSecurityService ssnSecurityService;
	@Autowired
	private PostsManager postsManager;

	private SpaInitialData loadData(String loggedUser) {
		List<String> friends = ssnSecurityService.getUsers();
		friends.remove(loggedUser);

		List<PostDto> postDtos = postsManager.getPosts(loggedUser);
		List<Post> posts = new ArrayList<Post>();
		for (PostDto postDto : postDtos)
			posts.add(new Post(postDto));

		try {
			return new SpaInitialData(loggedUser, friends, posts);
		} catch (JsonProcessingException ex) {
			SsnApplication.LOGGER.error(ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = { "/react", "/react/index", "/react/index.html" })
	@GetMapping
	public String react(Model model, Principal principal) {
		model.addAttribute("initialData", loadData(principal.getName()));
		return "react/index";
	}

	@RequestMapping(value = { "/angular", "/angular/index", "/angular/index.html" })
	@GetMapping
	public String angular(Model model, Principal principal) {
		model.addAttribute("initialData", loadData(principal.getName()));
		return "angular/index";
	}
}
