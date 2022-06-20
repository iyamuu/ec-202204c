package com.example.ecommerce_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ecommerce_c.form.UserForm;
import com.example.ecommerce_c.service.SignupService;

@Controller
public class SignupController {
	
	@Autowired
	private SignupService signupService;
	
	
	@GetMapping("/signup")
	public String getSignupPage(UserForm form) {
		
		return "login/signup";
	}
	
}
