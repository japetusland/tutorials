package com.giapyland.ssn.presentation.model;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.giapyland.ssn.presentation.api.model.Post;

public class SpaInitialData {
	private String loggedUser;
	private String friends;
	private String posts;

	public SpaInitialData(String loggedUser, List<String> friends, List<Post> posts)
			throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		this.loggedUser = objectMapper.writeValueAsString(loggedUser);
		this.friends = objectMapper.writeValueAsString(friends);
		this.posts = objectMapper.writeValueAsString(posts);
	}

	public String getLoggedUser() {
		return loggedUser;
	}

	public String getFriends() {
		return friends;
	}

	public String getPosts() {
		return posts;
	}
}
