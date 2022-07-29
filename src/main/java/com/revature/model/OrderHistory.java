package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orderhistory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistory {

	@Id
	@Column(name = "order_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "date")
	private String date;
	
	@OneToOne
	@JoinColumn(name = "acc_owner", referencedColumnName = "user_id")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "itin_id",referencedColumnName = "itinerary_id")
	private Itinerary itinerary;
	
}
