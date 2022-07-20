package com.revature.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.data.UserRepository;
import com.revature.exception.UserNotFoundException;
import com.revature.model.User;

@Service
public class UserService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserRepository userRepo;
	
	//methods that call upon the Repo
	public Set<User> findAll(){
		
		//return from user repo the findAll method but stream it to a set
		return userRepo.findAll().stream().collect(Collectors.toSet());
	}
	
	//Find by username
	public User getByUsername(String username) {
		
		return userRepo.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("No User found with username: " + username));
	}
	
	public User getById(int id) {
		if(id<=0) {
			log.warn("Id must be greater than zero: {}", id);
		}
		return userRepo.getById(id);
	}
	
	public User add(User u) {
		
		User returnedUser = userRepo.save(u);
		//getId() below is from lombok
		if(returnedUser.getId() > 0) {
			log.info("Successfully returned user with id {}", returnedUser.getId());
		}else {
			log.warn("Could not add user");
		}
		
		return returnedUser;
	}
	
	
	public User update(User u) {
		return userRepo.save(u);
	}
	
	public boolean delete(int id) {
		userRepo.deleteById(id);
		//validates if deletion went through
		return !(userRepo.existsById(id));
	}
	
	
	
	
	
}
