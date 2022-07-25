package com.revature.service;

import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.data.ItineraryRepository;
import com.revature.data.UserRepository;
import com.revature.exception.UserNotFoundException;
import com.revature.model.Itinerary;
import com.revature.model.User;

@Service
public class UserService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserRepository userRepo;

	@Autowired
	ItineraryRepository itinRepo;

	// methods that call upon the Repo
	public List<User> findAll() {

		// return from user repo the findAll method but stream it to a set
		return userRepo.findAll();
	}

	// Find by username
	public User getByUsername(String username) {

		return userRepo.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("No User found with username: " + username));
	}
	
	
	
	
	public User getById(int id) {
		if (id <= 0) {
			log.warn("Id must be greater than zero: {}", id);
		}
		return userRepo.findById(id).get();
	}

	public User add(User u) {

		User returnedUser = userRepo.save(u);
		// getId() below is from lombok
		if (returnedUser.getId() > 0) {
			log.info("Successfully returned user with id {}", returnedUser.getId());
		} else {
			log.warn("Could not add user");
		}

		return returnedUser;
	}

	public User update(User u) {
		userRepo.save(u);
		return u;
	}

	public boolean delete(int id) {
		userRepo.deleteById(id);
		// validates if deletion went through
		return !(userRepo.existsById(id));
	}

	
	
	public User addToCart(int user_id, int itin_id) {

		User u = userRepo.findById(user_id).get();
		Itinerary itin = itinRepo.findById(itin_id);
		List<Itinerary> cart = u.getItineraries();

		
		if (itin.getSlots() >= 1) {
			
			cart.add(itin);
			u.setItineraries(cart);
			
			itin.setSlots(itin.getSlots()-1);
			
			itinRepo.save(itin);
			userRepo.save(u);
			
			
			return u;
		}
		return null;
	}
	
	
	
	public boolean removeItemFromCart(int user_id, int itin_id) {
		User u = userRepo.findById(user_id).get();
		Itinerary itin = itinRepo.findById(itin_id);
		List<Itinerary> cart = u.getItineraries();
		
		if(cart.contains(itin)){
			cart.remove(itin);
			u.setItineraries(cart);
			
			userRepo.save(u);
			return true;
		}
		return false;
	}
	
	

}
