package com.revature.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.model.OrderHistory;
import com.revature.model.User;

@Repository
public interface OrderHistoryRepo extends JpaRepository<OrderHistory, Integer>{
	public OrderHistory findById(int id);
	
	@Query("FROM orderhistory WHERE acc_owner = ?1")
	public List<OrderHistory> getHistoryOfUser(int user_id);
	
	@Query(value = "DELETE FROM orderhistory WHERE acc_owner = ?1", nativeQuery = true)
	public void clearHistory();

}
