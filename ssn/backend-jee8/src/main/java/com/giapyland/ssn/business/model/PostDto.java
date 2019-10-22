package com.giapyland.ssn.business.model;

import java.util.ArrayList;
import java.util.List;

import com.giapyland.ssn.data_access.Comment;
import com.giapyland.ssn.data_access.Post;

public class PostDto {
	private long id;
	private String user;
	private String content;
	private long timestamp;
	private List<CommentDto> comments;

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

	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}

	public PostDto(Post post) {
		this.id = post.getId();
		this.user = post.getUser();
		this.content = post.getContent();
		this.timestamp = post.getTimestamp();
		this.comments = new ArrayList<CommentDto>();
		for (Comment comment : post.getComments()) {
			this.comments.add(new CommentDto(comment));
		}
	}
}
