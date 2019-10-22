package com.giapyland.ssn.configuration.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.giapyland.ssn.data_access.Role;
import com.giapyland.ssn.data_access.User;

public class SsnUser implements UserDetails {

	private static final long serialVersionUID = 1L;

	private User user;

	public SsnUser(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
		
		for (Role role : user.getRoles())
			list.add(new SimpleGrantedAuthority(role.getName()));
		
		return list;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
