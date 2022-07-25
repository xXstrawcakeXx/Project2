//package com.revature.model;
//
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//
//import org.hibernate.annotations.Formula;
//import org.springframework.beans.factory.annotation.Value;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//
//
//@Entity
//@Table(name="users_itin_cart")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Cart {
//	
//	@Id
//	@Column(name="cart_id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int Id;
//	
//	
//	@NotBlank
//	@NotNull
//	private User u;
//	
//	@ManyToMany(targetEntity = Itinerary.class)
//	@JoinColumn(name = "itinerary_id")
////	@Column(name = "itin_id")
//	private List<Itinerary> iList;
//	
////	@OneToOne(targetEntity = Itinerary.class)
////	@Min(value=0)
////	@JoinColumn(name = "price")
//	private int itPrice;
//	
//	@Min(value=0)
//	@Column(name = "subtotal")
//	private int sumPrice;
//
//	public Cart(@NotBlank @NotNull User u, List<Itinerary> iList, @Min(0) int itPrice, @Min(0) int sumPrice) {
//		super();
//		this.u = u;
//		this.iList = iList;
//		this.itPrice = itPrice;
//		this.sumPrice = sumPrice;
//	}
//
//	public Cart(@NotBlank @NotNull User u, List<Itinerary> iList) {
//		super();
//		this.u = u;
//		this.iList = iList;
//	}
//	
//}
