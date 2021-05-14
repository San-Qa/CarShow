package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.demo.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = { "classpath:user-schema.sql",
"classpath:user-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

@Autowired
private MockMvc mockMVC;

@Autowired
private ObjectMapper mapper;	


@Test
void testCreate() throws Exception {
	//Create a new car
	User user = new User("Audi", "RS5");
	//convert it to JSON
	String userJSON = this.mapper.writeValueAsString(user);
	
	RequestBuilder request = 
			post("/api/addUser")
			.contentType(MediaType.APPLICATION_JSON)
			.content(userJSON);
	
	//save car
	User saveUser = new User(2L,"Audi", "RS5");
	String saveUserJSON = this.mapper.writeValueAsString(saveUser);
	
	//201 status
	ResultMatcher match = status().isCreated();
	// check that response body 
	ResultMatcher body = content().json(saveUserJSON);
	
	this.mockMVC.perform(request).andExpect(match).andExpect(body);
	
	
}

@Test
void testList() throws Exception {
	User users = new User(1L, "BMW", "M2");
	List<User> listUser = List.of(users);
	
	String userAsJSON = this.mapper.writeValueAsString(listUser);
	
	RequestBuilder request = get("/api/users");
	
	ResultMatcher match = status().isOk();
	ResultMatcher body = content().json(userAsJSON);
	
	this.mockMVC.perform(request).andExpect(match).andExpect(body);
}          

@Test
void updateTest() throws Exception {
	User user = new User("Audi", "RS5");
	
	String userJSON = this.mapper.writeValueAsString(user);
	
	RequestBuilder mockRequest = put("/api/updateuser/1").contentType(MediaType.APPLICATION_JSON)
			.content(userJSON);
	
	User saveUser = new User(1L,"Audi", "RS5");
	
	String saveUserJASON = this.mapper.writeValueAsString(saveUser);
	
	ResultMatcher matchstatus = status().isOk();
	
	ResultMatcher matchBody = content().json(saveUserJASON);
	
	this.mockMVC.perform(mockRequest).andExpect(matchstatus).andExpect(matchBody);
} 


}
