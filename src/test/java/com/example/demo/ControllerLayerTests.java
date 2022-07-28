package com.example.demo;

import static org.mockito.Mockito.mock;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.revature.controller.UserController;
import com.revature.data.UserRepository;
import com.revature.model.User;
import com.revature.service.UserService;

@WebMvcTest(controllers = UserController.class)
public class ControllerLayerTests {
	
	

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService uServ;
	
	
	@MockBean 
	UserRepository uRep;
	
	
	@Test
	public void findByUsername() {
		User u = new User ("firstName","lastName","username","pass","email@email.com");
		String fName = u.getUsername();
		
		Mockito.when(uServ.getByUsername(fName).thenReturn(u));
		
	}
	
	
}
