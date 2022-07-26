package com.revature.data;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.model.Itinerary;
import com.revature.model.User;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Integer>{
	
	
	//public Optional<Itinerary> findById(int id);
	public Itinerary findById(int id);
	
	@Query("FROM Itinerary WHERE slots > 0")
	public List<Itinerary> getAvailableItineraries();
	
	@Query(value = "SELECT * FROM itineraries WHERE itinerary_id IN (SELECT itin_id FROM itin_tags WHERE tag_id = ?1)", nativeQuery = true)
	public List<Itinerary> findByTag(int tag_id);
	
	
	 
	
	@Modifying
	@Transactional
	@Query(value="UPDATE itineraries SET slots=slots-1 WHERE \"itinerary_id\" =:itiner_id AND slots>0", nativeQuery = true)
	public int addToCart(@Param("itiner_id") int itiner_id);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE itineraries SET slots=slots+1 WHERE itinerary_id=:it_id", nativeQuery = true)
	public int removeFromCart(@Param("it_id") int itiner_id);

	
	@Query(value = "SELECT * FROM itineraries WHERE active = false", nativeQuery = true)
	public List<Itinerary> getInactiveItineraries(); 
	
	
}
