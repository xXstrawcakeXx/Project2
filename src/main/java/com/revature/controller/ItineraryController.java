package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Itinerary;
import com.revature.model.Tag;
import com.revature.service.ItineraryService;

//WORKS ON POSTMAN
@RestController
@RequestMapping("/itineraries")
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
	
	
}
