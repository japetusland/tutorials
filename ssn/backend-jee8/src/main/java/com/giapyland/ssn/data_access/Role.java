package com.giapyland.ssn.data_access;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "ROLES")
@NamedQuery(name = "Role.findAll", query = "select r from Role r")
@NamedQuery(name = "Role.findByName", query = "select r from Role r where r.name=:name")
@NamedQuery(name = "Role.getByUser", query = "select distinct r from Role r inner join r.users user where user.email=:email")
public class Role implements Serializable {

	@Id
	private String name;

	@ManyToMany(mappedBy = "roles")
	private Collection<User> users = new ArrayList<User>();

	private static final long serialVersionUID = 1L;

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}
}
