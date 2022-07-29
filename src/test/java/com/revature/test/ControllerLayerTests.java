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
import com.revature.controller.UserController;
import com.revature.model.Itinerary;
import com.revature.model.User;
import com.revature.service.ItineraryService;
import com.revature.service.UserService;

@WebMvcTest(UserController.class)
public class ControllerLayerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService uServ;

	@MockBean
	private ItineraryService iServ;

//	@MockBean 
//	UserRepository uRep;

	@Test
	public void findByUsernameTest() throws Exception {
		User dumbU = new User("firstName", "lastName", "username", "pass", "email@email.com");
		String fName = dumbU.getUsername();

		Mockito.when(uServ.getByUsername(fName)).thenReturn(dumbU);

		mockMvc.perform(get("/users/find/username")).andExpect(status().isOk());
	}

	@Test
	public void findAllTest() throws Exception {
		User dumbU = new User("firstName", "lastName", "username", "pass", "email@email.com");
		User dumbU2 = new User("firstNameee", "lastNameee", "username", "pass", "email@email.com");
		List<User> uList = new LinkedList<User>();

		uList.add(dumbU);
		uList.add(dumbU2);

		Mockito.when(uServ.findAll()).thenReturn(uList);

		MvcResult result = mockMvc.perform(get("/users")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

		String resStr = result.getResponse().getContentAsString();
	}

//	@Test
//	public void findByUsernameRequestTest() throws Exception {
//		User dumbU = new User ("firstName","lastName","username","pass","email@email.com");
//		
//		
//		Mockito.when(uServ.getByUsername(dumbU.getUsername())).thenReturn(dumbU);
//		
//		mockMvc.perform(get("/users/findQuery?username"))
//				.andExpect(status().isOk());
//				//.andExpect(content().contentType(MediaType.APPLICATION_JSON))
//				//.andReturn();
//		
//		//String resStr = result.getResponse().getContentAsString();
//	}

	// POST REQUEST
	@Test
	public void addNewUserTest() throws Exception {
		User dumbU = new User("firstName", "lastName", "username", "pass", "email@email.com");

		Mockito.when(uServ.add(dumbU)).thenReturn(dumbU);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(dumbU);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/users")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(requestJson);

		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.username", is("username")));
	}

	// PUT REQUEST
	@Test
	public void updateUserTest() throws Exception {
		User dumbU = new User("firstName", "lastName", "username", "pass", "email@email.com");
		dumbU.setFirstName("firstNameFirstUpdate");

		Mockito.when(uServ.update(dumbU)).thenReturn(dumbU);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(dumbU);

		mockMvc.perform(put("/users/update").contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andExpect(status().isOk());

	}

	// DELETE REQUEST
	@Test
	public void deleteUserTest() throws Exception {
		User dumbU = new User("firstName", "lastName", "username", "pass", "email@email.com");
		dumbU.setId(1);

		Mockito.when(uServ.delete(dumbU.getId())).thenReturn(true);

		this.mockMvc.perform(delete("/users/delete/{id}", "1").contentType(MediaType.APPLICATION_JSON))
				// .accept(MediaType.APPLICATION_JSON)
				.andExpect(status().isOk());
	}

	// POST REQUEST
	@Test
	public void loginTest() throws Exception {
		User dumbU = new User("firstName", "lastName", "username", "pass", "email@email.com");

		Mockito.when(uServ.getByUsername(dumbU.getUsername())).thenReturn(dumbU);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(dumbU);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/users/login")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(requestJson);

		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.password", is("pass")));
	}

	// POST REQUEST
	@Test
	public void addToCartTest() throws Exception {
		User dumbU = new User("firstName", "lastName", "username", "pass", "email@email.com");

		Itinerary itin = new Itinerary("destination", 5.00, 5, 5.00, 5.00, "I just want to sleep");

		List<Itinerary> itinList = new LinkedList<Itinerary>();
		itinList.add(itin);
		dumbU.setItineraries(itinList);

		Mockito.when(uServ.addToCart(dumbU.getId(), itin.getId())).thenReturn(dumbU);

		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(dumbU);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.post("/users/addtocart/{itin_id}", dumbU.getItineraries().get(0).getId()).header("id", "0")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(requestJson);

		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$.itineraries[0].id", is(0)));
	}
	
	public void deleteFromCartTest() throws Exception{
		User dumbU = new User("firstName", "lastName", "username", "pass", "email@email.com");
		dumbU.setId(1);
		Itinerary itin = new Itinerary("destination", 5.00, 5, 5.00, 5.00, "I just want to sleep");

		List<Itinerary> itinList = new LinkedList<Itinerary>();
		itinList.add(itin);
		dumbU.setItineraries(itinList);

		Mockito.when(uServ.removeFromCart(itin.getId(),dumbU.getId())).thenReturn(true);

		
		
		this.mockMvc.perform(delete("/users/deletefromcart/{id}", "1").contentType(MediaType.APPLICATION_JSON))
				// .accept(MediaType.APPLICATION_JSON)
				.andExpect(status().isOk());
		
	}

}
