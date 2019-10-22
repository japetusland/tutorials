package com.giapyland.ssn.data_access;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
@NamedQuery(name="User.findAll", query="select u from User u")
@NamedQuery(name="User.findByEmail", query="select u from User u where u.email=:email")
@NamedQuery(
		name="User.getByRole", 
		query="select distinct u from User u inner join u.roles role where role.name=:role"
		)
public class User {
		
	@Id
	private String email;
	
	private String password;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="USERS_ROLES", 
			joinColumns= @JoinColumn(name="User", referencedColumnName="email"), 
			inverseJoinColumns= @JoinColumn(name="Role", referencedColumnName="name"))
	private Collection<Role> roles = new ArrayList<Role>();

	public User() {
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

}
