package com.pract.oauth.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

	@GetMapping
	public String sayHelloToFreeWorld() {

		return "Hello Free World";

	}

	@GetMapping("/restricted")
	public String sayHelloAferLogginToGoogle() {

		return "Hello Google User";
	}
}
