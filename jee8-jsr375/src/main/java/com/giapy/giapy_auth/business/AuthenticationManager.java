package com.giapy.giapy_auth.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.mindrot.jbcrypt.BCrypt;

import com.giapy.giapy_auth.application.ApplicationConfig;
import com.giapy.giapy_auth.data_access.Role;
import com.giapy.giapy_auth.data_access.SecurityDao;
import com.giapy.giapy_auth.data_access.User;

@Named
@RequestScoped
public class AuthenticationManager
{

	@EJB
	SecurityDao dao;
            
    public boolean login(String email, String password) {
		try {
			User user = dao.getUser(email);
			if (user==null)
				return false;
			return BCrypt.checkpw(password, user.getPassword());
		} catch (Exception e) {
			ApplicationConfig.LOGGER.error(e.getMessage());
			return false;
		}    	
    }
    
    public List<String> getRoles(String email) {
    	List<String> ret = new ArrayList<String>();
    	try {
    		List<Role> roles = dao.getRolesByUser(email);
    		for (Role role:roles) 
    			ret.add(role.getName());
    	} catch(Exception e) {
			ApplicationConfig.LOGGER.error(e.getMessage());
    	}
    	return ret;
    }

	public boolean createUser(String email, String password) {
		try {
			String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
			User user = new User(email,passwordHash);
			dao.insertUser(user);
			Role role = dao.getRole(ApplicationConfig.USER);
			dao.addUserToRole(user, role);
		} catch (Exception e) {
			ApplicationConfig.LOGGER.error(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean createRole(String roleName) {
		try {
			Role role = new Role(roleName);
			dao.insertRole(role);
		} catch (Exception e) {
			ApplicationConfig.LOGGER.error(e.getMessage());
			return false;
		}
		return true;
	}
	
	public List<String> getUsers() {
		try {
			List<User> users = dao.getUsers();
			List<String> ret = new ArrayList<String>();
			for (User user:users)
				ret.add(user.getEmail());
			return ret;
		} catch (Exception e) {
			ApplicationConfig.LOGGER.error(e.getMessage());
		}
		return new ArrayList<String>();
	}
	
	public List<String> getRoles() {
		try {
			List<Role> roles = dao.getRoles();
			List<String> ret = new ArrayList<String>();
			for (Role role:roles)
				ret.add(role.getName());
			return ret;
		} catch (Exception e) {
			ApplicationConfig.LOGGER.error(e.getMessage());
		}
		return new ArrayList<String>();
	}
	
	public boolean addUserToRole(String email, String roleName) {
		try {
			User user = dao.getUser(email);
			Role role = dao.getRole(roleName);
			dao.addUserToRole(user, role);
			return true;
		} catch (Exception e) {
			ApplicationConfig.LOGGER.error(e.getMessage());
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
			ApplicationConfig.LOGGER.error(e.getMessage());
			e.printStackTrace();
			return false;
		}	
	}
		
	public boolean accountExists(String email) {
		try {
			return dao.getUser(email) != null;
		} catch (Exception e) {
			ApplicationConfig.LOGGER.error(e.getMessage());
			e.printStackTrace();
			return false;
		}	
	}

}
