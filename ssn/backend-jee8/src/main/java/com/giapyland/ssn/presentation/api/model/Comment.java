package com.giapyland.ssn.presentation.api.model;

import com.giapyland.ssn.business.model.CommentDto;

public class Comment {
	private String user;
	private long id;
	private long postId;
	private String content;
	private long timestamp;

	public Comment() {
	}

	public Comment(CommentDto commentDto) {
		this.id = commentDto.getId();
		this.postId = commentDto.getPostId();
		this.user = commentDto.getUser();
		this.content = commentDto.getContent();
		this.timestamp = commentDto.getTimestamp();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
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
}
