package com.example.ecommerce_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.ecommerce_c.service.SignupService;

@Controller
public class SignupController {
	
	@Autowired
	private SignupService signupService;
	
	
}
