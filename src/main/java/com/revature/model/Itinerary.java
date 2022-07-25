package com.revature.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "itineraries")
@Data //generate getters/setters, toString, hashCode, and equals methods automatically
@NoArgsConstructor
@AllArgsConstructor
public class Itinerary {

	
	@Id
	@Column(name = "itinerary_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "destination")
	private String destination;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "slots")
	private int slots;
	
	@Column(name = "lattitude")
	private double lattitude;
	
	@Column(name = "longitude") 
	private double longitude;
	
	@Column(name = "description")
	private String description;
	
	@ManyToMany
	@JoinTable(
		name = "itin_tags",
		joinColumns = @JoinColumn(name = "itin_id"),
		inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
	private List<Tag> tags;

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSlots() {
		return slots;
	}

	public void setSlots(int slots) {
		this.slots = slots;
	}

	public double getLattitude() {
		return lattitude;
	}

	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getId() {
		return id;
	}

	public Itinerary(String destination, double price, int slots, double lattitude, double longitude,
			String description, List<Tag> tags) {
		super();
		this.destination = destination;
		this.price = price;
		this.slots = slots;
		this.lattitude = lattitude;
		this.longitude = longitude;
		this.description = description;
		this.tags = tags;
	}

	public Itinerary(String destination, double price, int slots, double lattitude, double longitude,
			String description) {
		super();
		this.destination = destination;
		this.price = price;
		this.slots = slots;
		this.lattitude = lattitude;
		this.longitude = longitude;
		this.description = description;
	}
	
	
	public double distanceToPoint(double lat, double lng) {
		double dlong = Math.toRadians(this.longitude - lng);
		double dlat = Math.toRadians(this.lattitude - lat);
		double ans = Math.pow(Math.sin(dlat/2),2) + Math.cos(Math.toRadians(this.lattitude))* 
				Math.cos(Math.toRadians(lat))*Math.pow(Math.sin(dlong/2),2);
		ans = 2*Math.asin(Math.sqrt(ans))*3956; // miles
		return(ans);
	}
	
	
	
	//include longitude and latitude to search for nearby locations
	
//	@Length(min=2)
//	private String firstName;
//	
//	@Length(min=2)
//	private String lastName;
//	
//	//Data validation: min length of 5 and alphanumeric
//	@NotBlank
//	@Length(min=5)
//	@Pattern(regexp = "[a-zA-Z][a-zA-Z0-9]*")
//	private String username;
//	
//	@NotBlank
//	private String password;
//	
//	//Data validation: well-formed email requirement (ex. must have @ sign)
//	@Email // from javax.validation.constrains. Every email must contain an @
//	private String email;
//
//	public User(@Length(min = 2) String firstName, @Length(min=2) String lastName,
//			@NotBlank @Length(min = 5) @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9]*") String userName,
//			@NotBlank String password, @Email String email) {
//		super();
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.username = userName;
//		this.password = password;
//		this.email = email;
//	}
}
