package com.revature.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.model.Itinerary;

public interface ItineraryRepository extends JpaRepository<Itinerary, Integer>{
	
	
	public Optional<Itinerary> findById(int id);
	
	@Query("FROM Itineraries WHERE slots > 0")
	public List<Itinerary> getAvailableItineraries();
	
	
}
