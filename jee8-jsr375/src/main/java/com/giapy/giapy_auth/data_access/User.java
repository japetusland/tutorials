package com.giapy.giapy_auth.data_access;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
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
public class User implements Serializable {
	
	@Id
	private String email;
	
	private String password;
	
	@ManyToMany
	@JoinTable(name="USERS_ROLES", 
			joinColumns= @JoinColumn(name="User", referencedColumnName="email"), 
			inverseJoinColumns= @JoinColumn(name="Role", referencedColumnName="name"))
	private Collection<Role> roles;
	
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.password;
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
 	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	
}
