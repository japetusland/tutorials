package com.giapy.giapy_auth.presentation;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.giapy.giapy_auth.business.AuthenticationManager;


@Named
@RequestScoped
public class Home {
	
	@Inject
	private AuthenticationManager authenticationManager; 

	private List<String> accounts;

	public List<String> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<String> accounts) {
		this.accounts = accounts;
	}
	
	@PostConstruct
	public void init() {
		accounts = authenticationManager.getUsers();
	}
	
}
