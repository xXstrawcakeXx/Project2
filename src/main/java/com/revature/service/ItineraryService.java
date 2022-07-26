package com.revature.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.data.ItineraryRepository;
import com.revature.model.Itinerary;
import com.revature.model.Tag;

@Service
public class ItineraryService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ItineraryRepository itinRepo;

	public List<Itinerary> findAll() {
		return(itinRepo.findAll());
	}
	
	public List<Itinerary> findAvailable() {
		return(itinRepo.getAvailableItineraries());
	}
	
	public List<Itinerary> findInactive() {
		return(itinRepo.getInactiveItineraries());
	}
	
	public List<Itinerary> findByTag(Tag tag) {
		return(itinRepo.findByTag(tag.getId()));
	}
	
	public Itinerary getById(Integer id) {
		return(itinRepo.findById(id).get());
	}
	
	public Itinerary add(Itinerary itin) {
		return(itinRepo.save(itin));
	}
	
	public Itinerary update(Itinerary itin) {
		return(itinRepo.save(itin));
	}
	
	public boolean delete(int id) {
		itinRepo.deleteById(id);
		return (!itinRepo.existsById(id));
	}
	
}
