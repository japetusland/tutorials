package com.giapyland.ssn.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.giapyland.ssn.data_access.SecurityDao;
import com.giapyland.ssn.data_access.User;

@Service
public class SsnUserDetailsService implements UserDetailsService {
	
	@Autowired
	private SecurityDao securityDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = securityDao.getUser(username);
		
		if (user==null)
			throw new UsernameNotFoundException("Cannot find user");
		
		return new SsnUser(user);
	}

}
