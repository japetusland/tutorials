package com.giapy.giapy_auth.application;

import java.util.HashSet;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

import com.giapy.giapy_auth.business.AuthenticationManager;

@Named
@ApplicationScoped
public class SecurityIdentityStore implements IdentityStore {
	
	@Inject 
	AuthenticationManager authenticationManager;

	@Override
	public CredentialValidationResult validate(Credential credential) {		
		UsernamePasswordCredential login = (UsernamePasswordCredential)credential;
	    String email = login.getCaller();
		String password = login.getPasswordAsString();
		
		if (authenticationManager.login(email, password))
			return new CredentialValidationResult(
					email,
					new HashSet<String>(authenticationManager.getRoles(email))
			);
	    
		return CredentialValidationResult.INVALID_RESULT;
	}

}
