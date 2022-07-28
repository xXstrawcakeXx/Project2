package com.revature.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	//Spring Data Jpa will provide some impl
	public Optional<User> findByUsername(String username);
	
	public List<User> findByOrderByLastName();
	
	@Query("FROM User WHERE email LIKE %:pattern%")
	public List<User> findByEmailContains(String pattern); //Lets us search user by email
	
	
}
