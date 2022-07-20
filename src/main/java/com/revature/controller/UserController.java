package com.revature.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.User;
import com.revature.service.UserService;

@RestController //automatically infers the return value of all methods will be within an HttpResponseBody
@RequestMapping("/users")  //makes all methods available through /users
public class UserController {

	@Autowired
	UserService userServ;
	
	@GetMapping // localhost:8080/users GET
	public Set<User> getAll(){ 
		//Spring Boot Web starter has Jackson Object Mapper automatically built in
		//This will be returned as JSON
		return userServ.findAll(); //find all from userService
	}
	
	@GetMapping("find/{username}") //localhost:8080/users/find/username GET we're going to extract the username
	public User findByUsername(@PathVariable("username") String username) {
		return userServ.getByUsername(username);
	}
	
	//Now everything below is not necessarily best practice but it'll be useful to know I guesssssssss
	
	//searches whatever is after "Query?"
	//if there is a "username" after Query?, then use String username from method parameter
	//can add if statements if "username" does not exist
	
	@GetMapping("findQuery") //localhost:8080/users/find/findQuery?username=TEST GET we're going to extract the username TEST
	public User findByUsernameRequest(@RequestParam("username") String username) {
		return userServ.getByUsername(username);
	}
	
	@GetMapping("findHeader") //localhost:8080/users/findHeader GET
	public User findByHeader(@RequestHeader("username") String username){
		return userServ.getByUsername(username);
	}
	
	
	@GetMapping("findHeader2")//localhost:8080/users/findHeader2 GET
	public User findByHeader2(@RequestHeader HttpHeaders httpHeaders){
		return userServ.getByUsername(httpHeaders.getFirst("username"));
	}
	
	
	//
	
}
