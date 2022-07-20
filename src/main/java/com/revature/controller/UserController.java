package com.revature.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.User;
import com.revature.service.UserService;

@RestController //automatically infers the return value of all methods will be within an HttpResponseBody
@RequestMapping("/users")  //makes all methods available through /users
public class UserController {

	@Autowired
	UserService userServ;
	
	@GetMapping
	public Set<User> getAll(){
		//Spring Boot Web starter has Jackson Object Mapper automatically built in
		//This will be returned as JSON
		return userServ.findAll(); //find all from userService
	}
}
