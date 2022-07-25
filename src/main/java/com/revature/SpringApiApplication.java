package com.revature;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.revature.model.Itinerary;
import com.revature.model.User;
import com.revature.service.ItineraryService;
import com.revature.service.UserService;

@SpringBootApplication
public class SpringApiApplication implements CommandLineRunner {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService uServ;
	
	@Autowired
	ItineraryService iServ;

	public static void main(String[] args) {

		SpringApplication.run(SpringApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		
		User u4New = new User("u4firstName", "u4LastName", "u4Username", "u4Password", "u4Email@email.com");
		User u = new User("uFirstName", "uLastName", "uUsername", "uPassword", "uEmail@email.com");
		Itinerary i = new Itinerary("Kabul", 0, 0, 0, 0, "Oasis");
		Itinerary warsaw = new Itinerary("Warsaw", 0, 20, 0, 0, "History");
		List<Itinerary> uItin = u.getItineraries();
//		iServ.add(warsaw);
//		iServ.add(i);
//		uServ.add(u);
//		uServ.add(u4New);
		
		
		//uServ.addToCart(uServ.getById(2).getId(), 2);
		
	
		
		
		
		
		

	}

}
