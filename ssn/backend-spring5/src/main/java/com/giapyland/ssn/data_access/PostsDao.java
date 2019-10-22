package com.giapyland.ssn.data_access;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostsDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void insertPost(Post post) {
		entityManager.persist(post);
	}

	public void updatePost(Post p) {
		Post post = getPost(p.getId());
		post.setContent(p.getContent());
		
		entityManager.merge(post);
	}

	public List<Post> getPostsByUser(String user) {
		TypedQuery<Post> query = entityManager.createNamedQuery("Post.findByUser", Post.class);
		query.setParameter("user", user);
		
		return query.getResultList();
	}

	public Post getPost(long id) {
		try {
			TypedQuery<Post> query = entityManager.createNamedQuery("Post.findById", Post.class);
			query.setParameter("id", id);
			
			return query.getSingleResult();	
		} catch (NoResultException ex) {
			return null;
		}
	}

	public void insertComment(long postId, Comment comment) {
		Post post = getPost(postId);
		comment.setPost(post);
		entityManager.persist(comment);
		
		post.getComments().add(comment);
		entityManager.merge(post);
	}

	public List<Comment> getCommentsByPost(long postId) {
		TypedQuery<Comment> query = entityManager.createNamedQuery("Comment.findByPost", Comment.class);
		query.setParameter("postId", postId);
		
		return query.getResultList();
	}
}
