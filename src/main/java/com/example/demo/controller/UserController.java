package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.UserService;
import com.example.demo.entity.User;

@RequestMapping("/api")
@RestController 
@CrossOrigin 
public class UserController {
	@Autowired
	private UserService userService;
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	// create User Car
	@PostMapping("/addUser")
	public ResponseEntity<User>addUser(@RequestBody User user){
		return new ResponseEntity<User>(this.userService.createUser(user), HttpStatus.CREATED);
	}
	
	//get user by Id
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable Long id) {
		return this.userService.getUserById(id);
	}
	
	// get user
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getUsers());
	}
	
	//update user
	@PutMapping("/updateuser/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User user) {
		return this.userService.updateUser(id, user);
	} 
	
	@DeleteMapping("/deleteuser/{id}")
	public boolean deletUser(@PathVariable Long id) {
		return this.userService.deleteUserById(id);
		
	}

}
