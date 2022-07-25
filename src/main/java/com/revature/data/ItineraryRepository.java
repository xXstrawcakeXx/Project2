package com.revature.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.model.Itinerary;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Integer>{
	
	
	//public Optional<Itinerary> findById(int id);
	public Itinerary findById(int id);
	
	@Query("FROM Itinerary WHERE slots > 0")
	public List<Itinerary> getAvailableItineraries();
	
	@Query(value = "SELECT * FROM itineraries WHERE itinerary_id IN (SELECT itin_id FROM itin_tags WHERE tag_id = ?1)", nativeQuery = true)
	public List<Itinerary> findByTag(int tag_id);
	
	
}
