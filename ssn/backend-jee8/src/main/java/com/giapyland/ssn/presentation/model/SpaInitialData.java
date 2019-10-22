package com.giapyland.ssn.presentation.model;

import java.io.Serializable;
import java.util.List;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import com.giapyland.ssn.presentation.api.model.Post;

public class SpaInitialData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String loggedUser;
	private String friends;
	private String posts;
	
	public SpaInitialData() {}

	public SpaInitialData(String loggedUser, List<String> friends, List<Post> posts) {
		Jsonb jb = JsonbBuilder.create();
		this.loggedUser = jb.toJson(loggedUser);
		this.friends = jb.toJson(friends);
		this.posts = jb.toJson(posts);
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
