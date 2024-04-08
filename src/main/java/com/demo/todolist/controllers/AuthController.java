package com.demo.todolist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
	
	@GetMapping({"/"})
	public String showLogin() {
		return "lists/login";
	}

}
