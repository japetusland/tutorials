package com.giapyland.ssn.business.model;

import com.giapyland.ssn.data_access.Comment;

public class CommentDto {
	private String user;
	private long id;
	private long postId;
	private String content;
	private long timestamp;

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

	public CommentDto(Comment comment) {
		this.id = comment.getId();
		this.postId = comment.getPost().getId();
		this.user = comment.getUser();
		this.content = comment.getContent();
		this.timestamp = comment.getTimestamp();
	}
}
