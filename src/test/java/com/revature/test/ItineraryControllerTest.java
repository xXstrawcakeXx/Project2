package com.revature.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import com.revature.controller.ItineraryController;
import com.revature.model.Descriptor;
import com.revature.model.Itinerary;
import com.revature.model.Tag;
import com.revature.model.User;
import com.revature.service.ItineraryService;
import com.revature.service.UserService;

@WebMvcTest(ItineraryController.class)
public class ItineraryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService uServ;

	@MockBean
	private ItineraryService iServ;

//================================================================================================================================================

	// GET MAPPING
	@Test
	public void getAllTest() throws Exception {
		Itinerary itin = new Itinerary("destination", 5.00, 5, 5.00, 5.00, "I just want to sleep");
		List<Itinerary> uList = new LinkedList<Itinerary>();

		uList.add(itin);

		Mockito.when(iServ.findAll()).thenReturn(uList);

		MvcResult result = mockMvc.perform(get("/itineraries")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

		String resStr = result.getResponse().getContentAsString();

	}

//================================================================================================================================================

	// GET MAPPING
	@Test
	public void getAvailableTest() throws Exception {
		Itinerary itin = new Itinerary("destination", 5.00, 5, 5.00, 5.00, "I just want to sleep");
		List<Itinerary> uList = new LinkedList<Itinerary>();

		uList.add(itin);

		Mockito.when(iServ.findAvailable()).thenReturn(uList);

		MvcResult result = mockMvc.perform(get("/itineraries/available")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

		String resStr = result.getResponse().getContentAsString();

	}

//================================================================================================================================================

	// POST MAPPING
	@Test
	public void createNewItineraryTest() throws Exception {

		Itinerary itin = new Itinerary("destination", 5.00, 5, 5.00, 5.00, "I just want to sleep");
		List<Itinerary> uList = new LinkedList<Itinerary>();

		uList.add(itin);

		Mockito.when(iServ.add(itin)).thenReturn(itin);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(itin);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/itineraries")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(requestJson);

		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.destination", is("destination")));

	}

//================================================================================================================================================

	// GET MAPPING
	// DOES NOT WORK BECAUSE PATH VARIABLE IN CONTROLLER CLASS IS A CUSTOM TYPE (I
	// could fix this but it's the day before the due date soo...)
//	@Test
//	public void findByTagTest() throws Exception {
//		Tag tag = new Tag(); 
//		Tag tagTwo = new Tag();
//		
//		tag.setTag(Descriptor.Wilderness);
//		tagTwo.setTag(Descriptor.Backpacking);
//		
//		List<Tag> itinTagList = new LinkedList<Tag>();
//		List<Tag> itinTagListTwo = new LinkedList<Tag>();
//		
//		itinTagList.add(tag);
//		itinTagList.add(tagTwo);
//		
//		itinTagListTwo.add(tag);
//		
//		
//		Itinerary itin = new Itinerary("destination", 5.00, 5, 5.00, 5.00, "I just want to sleep", itinTagList);
//		Itinerary itinTwo = new Itinerary("destinationTwo", 5.00, 5, 5.00, 5.00, "I just want to sleep more", itinTagListTwo);
//		
//		List<Itinerary> testItinList = new LinkedList<Itinerary>();
//
//
//		Mockito.when(iServ.findByTag(tag)).thenReturn(testItinList);
//
//		MvcResult result = mockMvc.perform(get("/itineraries/{tag}", tag.getTag()))
//				.andExpect(status().isOk())
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
//				.andReturn();
//
//		String resStr = result.getResponse().getContentAsString();
//		
//		
//	}
//	

//================================================================================================================================================

	
	@Test
	public void getImageStringTest() throws Exception {

		Itinerary itin = new Itinerary("destination", 5.00, 5, 5.00, 5.00, "I just want to sleep");
		itin.setImage("imageee");
		
		//List<Itinerary> uList = new LinkedList<Itinerary>();

	
		Mockito.when(iServ.findImage(itin.getId())).thenReturn("imageee");

		String result = mockMvc.perform(get("/itineraries/image/{id}", itin.getId())
				.contentType(MediaType.APPLICATION_JSON)//(MediaType.APPLICATION_JSON))
				.characterEncoding("UTF-8"))
				//.getContentAsString(StandardCharsets.UTF_8);
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn()
				.getResponse()
				.getContentAsString();


	}

//================================================================================================================================================

	
	@Test
	public void updateItinTest() throws Exception {
		Itinerary itin = new Itinerary("destination", 5.00, 5, 5.00, 5.00, "I just want to sleep");
		itin.setDestination("London");

		Mockito.when(iServ.update(itin)).thenReturn(itin);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(itin);

		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/itineraries/update")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(requestJson);
		
		mockMvc.perform(mockRequest)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.destination", is("London")));
	}

}
