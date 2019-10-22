package com.giapyland.ssn.business;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giapyland.ssn.SsnApplication;
import com.giapyland.ssn.data_access.Role;
import com.giapyland.ssn.data_access.SecurityDao;
import com.giapyland.ssn.data_access.User;

@Service
public class SsnSecurityService {

	public enum UserCreationResult {
		SUCCESS,
		USER_ALREADY_EXISTS,
		FAILURE
	}	
	
	@Autowired
	SecurityDao dao;

	public boolean login(String email, String password) {
		try {
			User user = dao.getUser(email);

			if (user == null)
				return false;

			if (BCrypt.checkpw(password, user.getPassword()))
				return true;

			return false;
		} catch (Exception e) {
			SsnApplication.LOGGER.error(e.getMessage());
			return false;
		}
	}

	public List<String> getRoles(String email) {
		List<String> ret = new ArrayList<String>();
		try {
			List<Role> roles = dao.getRolesByUser(email);

			for (Role role : roles)
				ret.add(role.getName());
		} catch (Exception e) {
			SsnApplication.LOGGER.error(e.getMessage());
		}
		return ret;
	}

	public UserCreationResult createUser(String email, String password) {
		try {
			if (dao.getUser(email) != null)
				return UserCreationResult.USER_ALREADY_EXISTS;
			String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
			User user = new User(email, passwordHash);
			if (createRole(SsnApplication.ROLE_USER)) {
				Role role = dao.getRole(SsnApplication.ROLE_USER);				
				dao.insertUserWithRole(user, role);
			}
		} catch (Exception e) {
			SsnApplication.LOGGER.error(e.getMessage());
			return UserCreationResult.FAILURE;
		}
		return UserCreationResult.SUCCESS;
	}

	public boolean createRole(String roleName) {
		try {
			if (dao.getRole(SsnApplication.ROLE_USER)!=null)
				return true;
			Role role = new Role(roleName);
			dao.insertRole(role);
		} catch (Exception e) {
			SsnApplication.LOGGER.error(e.getMessage());
			return false;
		}
		return true;
	}

	public List<String> getUsers() {
		List<String> ret = new ArrayList<String>();
		try {
			List<User> users = dao.getUsers();
			for (User user : users)
				ret.add(user.getEmail());
		} catch (Exception e) {
			SsnApplication.LOGGER.error(e.getMessage());
		}
		return ret;
	}

	public List<String> getRoles() {
		List<String> ret = new ArrayList<String>();
		try {
			List<Role> roles = dao.getRoles();
			for (Role role : roles)
				ret.add(role.getName());
		} catch (Exception e) {
			SsnApplication.LOGGER.error(e.getMessage());
		}
		return ret;
	}

	public boolean addUserToRole(String email, String roleName) {
		try {
			User user = dao.getUser(email);
			Role role = dao.getRole(roleName);
			dao.addUserToRole(user, role);
			return true;
		} catch (Exception e) {
			SsnApplication.LOGGER.error(e.getMessage());
			return false;
		}

	}

	public boolean removeUserFromRole(String email, String roleName) {
		try {
			User user = dao.getUser(email);
			Role role = dao.getRole(roleName);
			dao.removeUserFromRole(user, role);
			return true;
		} catch (Exception e) {
			SsnApplication.LOGGER.error(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
}
