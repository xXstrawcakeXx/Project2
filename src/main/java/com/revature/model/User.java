package com.revature.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Fetch;
import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// comes from Hibernate, I guess?
	@Length(min = 2)
	@Column(name = "first_name")
	private String firstName;

	@Length(min = 2)
	@Column(name = "last_name")
	private String lastName;

	// Data validation: min length of 5 and alphanumeric
	@NotBlank
	@Length(min = 5)
	@Pattern(regexp = "[a-zA-Z][a-zA-Z0-9]*")
	private String username;

	@NotBlank
	@Column(name = "pword")
	private String password;

	// Data validation: well-formed email requirement (ex. must have @ sign)
	@Email // from javax.validation.constrains. Every email must contain an @
	@Column(name = "email", unique = true)
	private String email;

	@ManyToMany
	@JoinTable(
		name = "us_itin_cart", 
		joinColumns = @JoinColumn(name = "us_id"),
		inverseJoinColumns = @JoinColumn(name = "itin_id"))
	List<Itinerary> itineraries;
	
	

	
	
	public User(@Length(min = 2) String firstName, @Length(min = 2) String lastName,
			@NotBlank @Length(min = 5) @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9]*") String userName,
			@NotBlank String password, @Email String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = userName;
		this.password = password;
		this.email = email;
	}

	public User(@Length(min = 2) String firstName, @Length(min = 2) String lastName,
			@NotBlank @Length(min = 5) @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9]*") String userName,
			@NotBlank String password, @Email String email, List<Itinerary> itineraries) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = userName;
		this.password = password;
		this.email = email;
		this.itineraries = itineraries;
	}

}
