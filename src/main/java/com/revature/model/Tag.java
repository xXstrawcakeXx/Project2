package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tags")
@Data
public class Tag {
	@Id
	@Column(name = "tag_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tag_type")
	private Descriptor tag;

	public int getId() {
		return id;
	}

	public Descriptor getTag() {
		return tag;
	}
	
	
}
