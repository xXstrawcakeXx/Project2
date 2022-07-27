package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Itinerary;
import com.revature.model.Tag;
import com.revature.model.User;
import com.revature.service.ItineraryService;

//WORKS ON POSTMAN
@RestController
@RequestMapping("/itineraries")
@CrossOrigin("*") // Helps with endpoint 
public class ItineraryController {
	
	@Autowired
	ItineraryService itinServ;
	
	//WORKS ON POSTMAN (SAME AS @RequestMapping /itineraries)
	@GetMapping
	public List<Itinerary> getAll() {
		return itinServ.findAll();
	}
	
	//WORKS ON POSTMAN
	@GetMapping("available")
	public List<Itinerary> getAvailable() {
		return itinServ.findAvailable();
	}
	
	//WORKS ON POSTMAN
	@PostMapping
	public Itinerary createNewItinerary(@RequestBody Itinerary i) {
		return itinServ.add(i);
	}
	
	//not tested yet
	@GetMapping("{tag}")
	public List<Itinerary> findByTag(@PathVariable("tag") Tag tag) {
		return(itinServ.findByTag(tag));
	}
	
	@GetMapping("image/{id}")
	public String getImageString(@PathVariable("id") int id) {
		return (itinServ.findImage(id));
	}
	
	@PutMapping("update") // localhost:8080/users/update
	public Itinerary updateUser(@RequestBody Itinerary i) {
		return itinServ.update(i);
	}
}
