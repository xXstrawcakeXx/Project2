package com.revature.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.data.OrderHistoryRepo;
import com.revature.data.UserRepository;
import com.revature.model.Itinerary;
import com.revature.model.OrderHistory;
import com.revature.model.User;

@Service
public class OrderHistoryService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	OrderHistoryRepo ohrep;
	
	@Autowired
	UserRepository ur;
	
	public List<OrderHistory> findAll() {
		return ohrep.findAll();
	}
	
	public List<OrderHistory> getByUser(User u) {
		return ohrep.getHistoryOfUser(u.getId());
	}
	
	public void add(OrderHistory oh) {
		ohrep.save(oh);
	}
	
	public void saveAll(List<Itinerary> itinList, User u) {
		for(Itinerary i : itinList) {
			OrderHistory temp = new OrderHistory();
			temp.setItinerary(i);
			temp.setDate(LocalDateTime.now().toString());
			temp.setPrice(i.getPrice());
			temp.setUser(u);
			ohrep.save(temp);
		}
	}
}
