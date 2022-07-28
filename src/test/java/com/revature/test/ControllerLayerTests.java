package com.revature.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.revature.controller.UserController;
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
		User dumbU = new User ("firstName","lastName","username","pass","email@email.com");
		String fName = dumbU.getUsername();
		
		Mockito.when(uServ.getByUsername(fName)).thenReturn(dumbU);
		
		mockMvc.perform(get("/users/find/username"))
				.andExpect(status().isOk());
	}
	
	
	@Test
	public void findAllTest() throws Exception {
		User dumbU = new User ("firstName","lastName","username","pass","email@email.com");
		User dumbU2 = new User ("firstNameee","lastNameee","username","pass","email@email.com");
		List<User> uList = new LinkedList<User>();
		
			uList.add(dumbU);
			uList.add(dumbU2);
		
		String fName = dumbU.getUsername();
		
		Mockito.when(uServ.findAll()).thenReturn(uList);
		
		MvcResult result = mockMvc.perform(get("/users"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		
		String resStr = result.getResponse().getContentAsString();
	}
	
	
}
