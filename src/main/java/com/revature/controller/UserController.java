package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.User;
import com.revature.service.UserService;

@RestController // automatically infers the return value of all methods will be within an
				// HttpResponseBody
@RequestMapping("/users") // makes all methods available through /users
@CrossOrigin("*") // Helps with connecting endpoints

public class UserController {

	@Autowired
	UserService userServ;

	//WORKS ON POSTMAN
	@GetMapping // localhost:8080/users GET
	public List<User> getAll() {
		// Spring Boot Web starter has Jackson Object Mapper automatically built in
		// This will be returned as JSON
		return userServ.findAll(); // find all from userService
	}

	//WORKS ON POSTMAN
	@GetMapping("find/{username}") // localhost:8080/users/find/username GET we're going to extract the username
	public User findByUsername(@PathVariable("username") String username) {
		return userServ.getByUsername(username);
	}


	//WORKS ON POSTMAN
	@GetMapping("findQuery") // localhost:8080/users/findQuery?username=TEST GET we're going to extract
								// the username TEST
	public User findByUsernameRequest(@RequestParam("username") String username) {
		return userServ.getByUsername(username);
	}

	//WORKS ON POSTMAN (USERNAME AND EMAIL MUST BE UNIQUE OR WILL RETURN AN ERROR)
	@PostMapping
	public User addNewUser(@RequestBody User user) {
		return userServ.add(user);
	}

	//WORKS ON POSTMAN
	@PutMapping("update") // localhost:8080/users/update
	public User updateUser(@RequestBody User u) {
		return userServ.update(u);
	}

	//WORKS ON POSTMAN
	@DeleteMapping("delete/{id}") // localhost:8080/users/delete/1
	public boolean deleteUser(@PathVariable("id") int id) {
		return userServ.delete(id);
	}


	//WORKS ON POSTMAN (IF USER ENTERS WRONG PASSWORD, GET ERROR)
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody LoginObj loginObj) {

		
		User u = userServ.getByUsername(loginObj.username);
		if (!u.getPassword().equals(loginObj.password)) {

			return new ResponseEntity<String>("Unable to Log User In", HttpStatus.BAD_REQUEST);
			//return new ResponseEntity<String>("Unable to Log User In", headers, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}
	
	
	@PostMapping("addtocart/{itin_id}")
	public ResponseEntity<User> addToCart(@PathVariable("itin_id") int itin_id, @RequestHeader("id") int id){
		User u = userServ.addToCart(id, itin_id);
		
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}
	
	
	@DeleteMapping("deletefromcart/{itin_id}")
	public ResponseEntity<User> deleteFromCart(@PathVariable("itin_id") int itin_id, @RequestHeader("id") int id){
		User u = userServ.getById(id);
		userServ.removeFromCart(itin_id, id);
		
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}
	
	@DeleteMapping("checkout/{user_id}")
	public ResponseEntity<User> checkout(@PathVariable("user_id") int user_id){
		User u = userServ.getById(user_id);
		userServ.checkout(user_id);
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}
}

class LoginObj {
	public String username;
	public String password;

}