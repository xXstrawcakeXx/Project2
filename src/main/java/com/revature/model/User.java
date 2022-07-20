package com.revature.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "users")
@Data //generate getters/setters, toString, hashCode, and equals methods automatically
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//data validation: length of fields must be >1
	//comes from Hibernate, I guess?
	@Length(min=2)
	private String firstName;
	
	@Length(min=2)
	private String lastName;
	
	//Data validation: min length of 5 and alphanumeric
	@NotBlank
	@Length(min=5)
	@Pattern(regexp = "[a-zA-Z][a-zA-Z0-9]*")
	private String username;
	
	@NotBlank
	private String password;
	
	//Data validation: well-formed email requirement (ex. must have @ sign)
	@Email // from javax.validation.constrains. Every email must contain an @
	private String email;

	public User(@Length(min = 2) String firstName, @Length(min=2) String lastName,
			@NotBlank @Length(min = 5) @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9]*") String userName,
			@NotBlank String password, @Email String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = userName;
		this.password = password;
		this.email = email;
	}
	
	
	
}
