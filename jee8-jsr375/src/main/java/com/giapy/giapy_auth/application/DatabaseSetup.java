package com.giapy.giapy_auth.application;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.giapy.giapy_auth.business.AuthenticationManager;

@Singleton
@Startup
public class DatabaseSetup {

	@Inject
	AuthenticationManager authenticationManager;
	
    @PostConstruct
    public void init() {
    	try {
    		if (authenticationManager.getRoles().isEmpty()) {
    			authenticationManager.createRole(ApplicationConfig.ADMIN);
    			authenticationManager.createRole(ApplicationConfig.USER);
    			authenticationManager.createUser("admin@admin.com", "admin");
    			authenticationManager.addUserToRole("admin@admin.com", ApplicationConfig.ADMIN);
    		}
		} catch (Exception e) {
			ApplicationConfig.LOGGER.error("CANNOT INITIALIZE THE DATABASE: " + e.getMessage());
		}
    }
	
}
