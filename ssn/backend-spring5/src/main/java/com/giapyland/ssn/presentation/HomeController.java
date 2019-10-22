package com.giapyland.ssn.presentation;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = { "/", "/index" })
public class HomeController {
	@GetMapping
	public String index(Model model, Principal principal) {
		model.addAttribute("user", principal.getName());
		return "index";
	}
}
