package com.giapyland.ssn.presentation;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.giapyland.ssn.business.SsnSecurityService;
import com.giapyland.ssn.presentation.model.UserRegistration;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	private SsnSecurityService ssnSecurityService;

	@Autowired
	private MessageSource messageSource;

	@GetMapping
	public String registrationForm(Model model) {
		model.addAttribute("userRegistration", new UserRegistration());

		return "registration";
	}

	@PostMapping
	public String registrationSubmit(@Valid UserRegistration user, BindingResult bindingResult, Locale locale) {

		if (bindingResult.hasErrors())
			return "registration";

		SsnSecurityService.UserCreationResult registrationResult = ssnSecurityService.createUser(user.getEmail(), user.getPassword());

		if (registrationResult.equals(SsnSecurityService.UserCreationResult.USER_ALREADY_EXISTS)) {
			bindingResult.reject("401",
					messageSource.getMessage("com.giapyland.ssn.userAlreadyExists", null, locale));
			return "registration";
		}

		if (registrationResult.equals(SsnSecurityService.UserCreationResult.FAILURE)) {
			bindingResult.reject("401", messageSource.getMessage("giapyland.ssn.cannotRegisterUser", null, locale));
			return "registration";
		}
		return "redirect:/";
	}

}
