package com.giapyland.ssn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SsnApplication {

	public static final Logger LOGGER = LogManager.getLogger(SsnApplication.class);
	public static final String ROLE_USER = "USER";

	public static void main(String[] args) {
		SpringApplication.run(SsnApplication.class, args);
	}
}
