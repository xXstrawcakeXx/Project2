package com.revature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.revature.model.User;
import com.revature.service.UserService;

@SpringBootApplication
public class SpringApiApplication implements CommandLineRunner{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserService userServ;
	
	public static void main(String[] args) {
		
		SpringApplication.run(SpringApiApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		User u = new User("Bryan", "Ser", "NotThatBryan", "SomeoneElse", "BryanSer@gmail.com");
		User uTwo = new User("NotBryan", "NotSer", "ThatBryan", "SomeoneElse", "NotBryanSer@gmail.com");
		
		userServ.add(u);
		userServ.add(uTwo);
	}

	
}
