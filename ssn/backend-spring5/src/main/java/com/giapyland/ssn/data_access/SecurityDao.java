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
public class SecurityDao {

	@PersistenceContext
	private EntityManager entityManager;

	public void insertUser(User user) {
		entityManager.persist(user);
	}

	public void insertRole(Role role) {
		entityManager.persist(role);
	}

	public void insertUserWithRole(User user, Role role) {
		user.getRoles().add(role);
		entityManager.persist(user);
		
		role.getUsers().add(user);
		entityManager.merge(role);
	}

	public void updateUser(User user) {
		entityManager.merge(user);
	}

	public void updateRole(Role role) {
		entityManager.merge(role);
	}

	public void deleteUser(User user) {
		entityManager.remove(user);
	}

	public void deleteRole(Role role) {
		entityManager.remove(role);
	}

	public void addUserToRole(User user, Role role) {
		user.getRoles().add(role);
		entityManager.merge(user);
		
		role.getUsers().add(user);
		entityManager.merge(role);
	}

	public void removeUserFromRole(User user, Role role) {
		user.getRoles().remove(role);
		entityManager.merge(role);
		
		role.getUsers().remove(user);
		entityManager.merge(user);
	}

	public List<User> getUsers() {
		return entityManager.createNamedQuery("User.findAll", User.class).getResultList();
	}

	public List<Role> getRoles() {
		return entityManager.createNamedQuery("Role.findAll", Role.class).getResultList();
	}

	public List<User> getUsersByRole(String roleName) {
		TypedQuery<User> query = entityManager.createNamedQuery("User.getByRole", User.class);
		query.setParameter("role", roleName);
		
		return query.getResultList();
	}

	public List<Role> getRolesByUser(String email) {
		TypedQuery<Role> query = entityManager.createNamedQuery("Role.getByUser", Role.class);
		query.setParameter("email", email);
		
		return query.getResultList();
	}

	public User getUser(String email) {
		try {
			TypedQuery<User> query = entityManager.createNamedQuery("User.findByEmail", User.class);
			query.setParameter("email", email);
			
			return query.getSingleResult();
			
		} catch (NoResultException ex) {
			return null;
		}
	}

	public Role getRole(String roleName) {
		try {
			TypedQuery<Role> query = entityManager.createNamedQuery("Role.findByName", Role.class);
			query.setParameter("name", roleName);
			
			return (Role) query.getSingleResult();
			
		} catch (NoResultException ex) {
			return null;
		}
	}

}
