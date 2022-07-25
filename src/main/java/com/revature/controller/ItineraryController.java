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

@RestController
@RequestMapping("/itineraries")
public class ItineraryController {
	
	@Autowired
	ItineraryService itinServ;
	
	@GetMapping
	public List<Itinerary> getAll() {
		return itinServ.findAll();
	}
	
	@GetMapping("available")
	public List<Itinerary> getAvailable() {
		return itinServ.findAvailable();
	}
	
	@PostMapping
	public Itinerary createNewItinerary(@RequestBody Itinerary i) {
		return itinServ.add(i);
	}
	
	@GetMapping("{tag}")
	public List<Itinerary> findByTag(@PathVariable("tag") Tag tag) {
		return(itinServ.findByTag(tag));
	}
	
	
}
