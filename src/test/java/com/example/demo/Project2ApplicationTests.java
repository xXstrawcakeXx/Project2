package com.example.demo;

import org.junit.jupiter.api.Test;


import com.revature.model.Itinerary;


class Project2ApplicationTests {

	
	@Test
	public void distanceToPoint() {
	Itinerary testItin = new Itinerary("Memorial Park", 120, 5, 29.7661730388124, -95.4417629014812, null);
	System.out.println(testItin.distanceToPoint(29.770899077659347, -95.08628758874619));
	}
}
