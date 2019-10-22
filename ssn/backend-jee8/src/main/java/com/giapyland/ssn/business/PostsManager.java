package com.giapyland.ssn.business;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.giapyland.ssn.SsnApplication;
import com.giapyland.ssn.business.model.CommentDto;
import com.giapyland.ssn.business.model.PostDto;
import com.giapyland.ssn.data_access.Comment;
import com.giapyland.ssn.data_access.Post;
import com.giapyland.ssn.data_access.PostsDao;

@Named
@RequestScoped
public class PostsManager {

	@EJB
	PostsDao dao;

	private long getTimestamp() {
		return LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
	}

	public boolean addPost(String user, String content) {
		try {
			Post post = new Post();
			post.setUser(user);
			post.setContent(content);
			post.setTimestamp(getTimestamp());
			dao.insertPost(post);
			return true;
		} catch (Exception ex) {
			SsnApplication.LOGGER.error(ex.getMessage());
			return false;
		}
	}

	public boolean updatePost(long id, String content) {
		try {
			Post post = new Post();
			post.setId(id);
			post.setContent(content);
			dao.updatePost(post);
			return true;
		} catch (Exception ex) {
			SsnApplication.LOGGER.error(ex.getMessage());
			return false;
		}
	}

	public List<PostDto> getPosts(String user) {
		List<PostDto> result = new ArrayList<PostDto>();
		try {
			List<Post> posts = dao.getPostsByUser(user);
			for (Post post : posts)
				result.add(new PostDto(post));
		} catch (Exception ex) {
			SsnApplication.LOGGER.error(ex.getMessage());
		}
		return result;
	}

	public PostDto getPost(long id) {
		PostDto result = null;
		try {
			Post post = dao.getPost(id);
			if (post == null)
				return null;
			result = new PostDto(post);
		} catch (Exception ex) {
			SsnApplication.LOGGER.error(ex.getMessage());
		}
		return result;
	}

	public List<CommentDto> getComments(long postId) {
		List<CommentDto> result = new ArrayList<CommentDto>();
		try {
			List<Comment> comments = dao.getCommentsByPost(postId);
			for (Comment comment : comments)
				result.add(new CommentDto(comment));
		} catch (Exception ex) {
			SsnApplication.LOGGER.error(ex.getMessage());
		}
		return result;
	}

	public boolean addComment(long postId, String user, String content) {
		try {
			Comment comment = new Comment();
			comment.setUser(user);
			comment.setContent(content);
			comment.setTimestamp(getTimestamp());
			dao.insertComment(postId, comment);
			return true;
		} catch (Exception ex) {
			SsnApplication.LOGGER.error(ex.getMessage());
			return false;
		}
	}

}
