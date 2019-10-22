package com.giapyland.ssn.presentation.api.model;

import java.util.ArrayList;
import java.util.List;

import com.giapyland.ssn.business.model.CommentDto;
import com.giapyland.ssn.business.model.PostDto;

public class Post {
	private long id;
	private String user;
	private String content;
	private long timestamp;
	private List<Comment> comments;

	public Post() {
	}

	public Post(PostDto postDto) {
		this.id = postDto.getId();
		this.user = postDto.getUser();
		this.content = postDto.getContent();
		this.timestamp = postDto.getTimestamp();
		this.comments = new ArrayList<Comment>();
		for (CommentDto commentDto : postDto.getComments())
			this.comments.add(new Comment(commentDto));
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
