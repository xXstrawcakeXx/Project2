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
//		return userRepo.update(u.getEmail(), u.getFirstName(), u.getLastName(), u.getPassword(), u.getUsername(),
//				u.getId());
		return userRepo.save(u);
	}

	
	
	public boolean delete(int id) {
		userRepo.deleteById(id);
		// validates if deletion went through
		return !(userRepo.existsById(id));
	}

	
	
	public List<Itinerary> getCartByUserId(int us_id) {
		List<Itinerary> cart = userRepo.findById(us_id).get().getItineraries();
		return cart;
	}

	
	
	public User addToCart(int user_id, int itin_id) {

		User u = userRepo.findById(user_id).get();
		Itinerary itin = itinRepo.findById(itin_id);

		if (itin.getSlots() >= 1) {

			List<Itinerary> cart = u.getItineraries();
			cart.add(itin);
			u.setItineraries(cart);

			//itinRepo.addToCart(itin_id);

			//itin.setSlots(itin.getSlots() - 1);
			//userRepo.update(u.getEmail(), u.getFirstName(), u.getLastName(), u.getPassword(), u.getUsername(),
			//		u.getId());
			userRepo.save(u);

			return u;
		}
		return null;
	}

	
	
	public boolean removeFromCart(int user_id, int itin_id) {
		User u = userRepo.findById(user_id).get();
		Itinerary itin = itinRepo.findById(itin_id);
		List<Itinerary> cart = u.getItineraries();

		if (cart.contains(itin)) {
			cart.remove(itin);
			u.setItineraries(cart);

//			userRepo.update(u.getEmail(), u.getFirstName(), u.getLastName(), u.getPassword(), u.getUsername(),
//					u.getId());
			userRepo.save(u);
			return true;
		}
		return false;
	}
	
	
	public User checkout(int user_id) {
		User u = userRepo.findById(user_id).get();
		//Itinerary itin = itinRepo.findById(itin_id);
		List<Itinerary> cart = u.getItineraries();
		
		//return null if any of the items in the cart have <=0 slots
		for(Itinerary it: cart) {
			if(it.getSlots()<=0) {
				return null;
			}
		}
		OrderHistoryService.saveAll(cart, userRepo.findById(user_id).get());
		//if not returned null from previous for-loop, then decrease all itin slots in cart by 1
		for(Itinerary it: cart) {
				itinRepo.decreaseItinSlot(it.getId());
		}
		
		//clear cart and set java object User u's cart to the empty cart
		cart.clear();
		u.setItineraries(cart);
		
		//now save User u's itinerary changes to the database and return u
		userRepo.save(u);
		return u;
	}

}
