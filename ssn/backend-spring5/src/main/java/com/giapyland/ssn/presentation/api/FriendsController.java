package com.giapyland.ssn.presentation.api;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giapyland.ssn.business.SsnSecurityService;

@RestController
@RequestMapping("/api/friends")
public class FriendsController {

	@Autowired
	private SsnSecurityService ssnSecurityService;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<String>> getUsers(Principal principal) {
		List<String> users = ssnSecurityService.getUsers();
		users.remove(principal.getName());
		
		return ResponseEntity.ok(users);
	}
}
