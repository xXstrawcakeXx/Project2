package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.data.ItineraryRepository;
import com.revature.data.OrderHistoryRepo;
import com.revature.data.UserRepository;
import com.revature.model.Itinerary;
import com.revature.model.OrderHistory;
import com.revature.model.User;
import com.revature.service.ItineraryService;
import com.revature.service.OrderHistoryService;
import com.revature.service.UserService;

@ExtendWith(MockitoExtension.class)
public class ServiceLayerTests {
	
	private User user;
	private List<Itinerary> itinList;
	
	@Mock
	private UserRepository ur;
	
	@Mock
	private ItineraryRepository ir;
	
	@Mock
	private OrderHistoryRepo ohr;
	
	@InjectMocks
	private UserService us;
	
	@InjectMocks
	private ItineraryService itins;
	
	@InjectMocks
	private OrderHistoryService ohs;
	
	@BeforeEach
	void setup() {
		itinList = new ArrayList<>();
		itinList.add(new Itinerary(1, "The UpsideDown", true, 666, 10, 0, 0, "become the flea", null, null));
		itinList.add(new Itinerary(2, "Atlantis", true, 75.3, 100, 0, 0, "A one way trip", null, null));
		user = new User(1,"Chuck", "Testa", "AwesomePieMan", "1337gamer", "OccamsChainsaw@gmail.com",itinList);
	}
	@AfterEach
	void teardown() {
		user = null;
		itinList = null;
	}
	
	@Test
	void shouldSaveUser() {
		given(ur.save(user)).willAnswer(invocation -> invocation.getArgument(0));
		User savedUser = us.add(user);
		assertEquals(savedUser,user);
	}
	
	@Test
	void updateUser() {
		given(ur.save(user)).willAnswer(invocation -> invocation.getArgument(0));
		User expected = us.update(user);
		assertEquals(expected,user);
	}
	
	@Test
	void shouldReturnFindAll() {
		List<User> users = new ArrayList<>();
		users.add(user);
		users.add(new User(2,"Charlie","dwaboutit","bitmyfinger","hushpuppy","idiotbox@hotmail.com",null));
		users.add(new User(3,"Amber","Teigrov","TheOutrider","Eula<3","ImNotBurnyGirl@hotmail.com",null));
		given(ur.findAll()).willReturn(users);
		List<User> expected = us.findAll();
		assertEquals(users, expected);
	}
	
	@Test
	void findUserById() {
		int uid = user.getId();
		//System.out.println(uid);
		given(ur.findById(uid)).willReturn(Optional.of(user));
		User expected = us.getById(uid);
		assertEquals(expected,user);
	}
	
	@Test
	void deleteUser() {
		given(ur.existsById(user.getId())).willReturn(false);
		assertEquals(us.delete(user.getId()),true);
	}
	
	@Test
	void getUserByUsername() {
		given(ur.findByUsername(user.getUsername())).willReturn(Optional.of(user));
		assertEquals(us.getByUsername(user.getUsername()),user);
	}
	
	@Test
	void getCartByUserId() {
		when(ur.findById(user.getId())).thenReturn(Optional.of(user));
		//given(ur.findById(user.getId())).willReturn(Optional.of(user));
		assertEquals(itinList, us.getCartByUserId(user.getId()));
	}
	
	@Test
	void addToCart() {
		Itinerary tempItin = new Itinerary(4, "H E DOUBLE HOCKEY STICKS", true, 1000, 6, 0, 0, "you know where", null, null);
		given(ur.findById(user.getId())).willReturn(Optional.of(user));
		given(ir.findById(tempItin.getId())).willReturn(tempItin);
		given(ur.save(user)).willAnswer(invocation -> invocation.getArgument(0));
		itinList.add(tempItin);
		assertEquals(itinList,us.addToCart(user.getId(), tempItin.getId()).getItineraries());
	}
	
	@Test
	void removeFromCart() {
		Itinerary tempItin = new Itinerary(4, "H E DOUBLE HOCKEY STICKS", true, 1000, 6, 0, 0, "you know where", null, null);
		itinList.add(tempItin);
		user.setItineraries(itinList);
		//System.out.println(user.getItineraries());
		when(ur.findById(user.getId())).thenReturn(Optional.of(user));
		given(ir.findById(4)).willReturn(tempItin);
		assertEquals(us.removeFromCart(user.getId(), tempItin.getId()), true);
	}
	
	@Test
	void userCheckout() {
		given(ur.findById(user.getId())).willReturn(Optional.of(user));
		given(ur.save(user)).willReturn(user);
		when(ohr.save(any(OrderHistory.class))).thenAnswer(invocation -> invocation.getArgument(0));
		us.checkout(user.getId());
		assertEquals(user.getItineraries(), new ArrayList<>());
	}
	
	
}
