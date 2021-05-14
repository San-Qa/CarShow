package com.example.demo.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;

@SpringBootTest
class UserServiceUnitTest {

	@Autowired
	private UserService service;
	
	@MockBean
	private UserRepository repository;
	
	@Test
	void testCreateUser() {
		
		User car = new User("BMW", "M3");
		User savedCar = new User(1L, "BMW", "M3");
		
		Mockito.when(this.repository.save(car)).thenReturn(savedCar);
		
		assertThat(this.service.createUser(car)).isEqualTo(savedCar);
		
		Mockito.verify(this.repository, Mockito.times(1)).save(car);
	}

	@Test
	void testGetUserById() {
		User user = new User(2L,"BMW", "X5");
		Mockito.when(repository.findById(2L)).thenReturn(Optional.of(user));
	}
	
	@Test
	void testUpdateUser() {
		Long id = 1L;
		
		User user = new User("Jag", "Z4");
		//wrapper
		Optional<User>optionalUser = Optional.of(new User(id, null, null));
		User updateUser = new User(id, user.getName(), user.getModel());
		
		Mockito.when(this.repository.findById(id)).thenReturn(optionalUser);
		Mockito.when(this.repository.save(updateUser)).thenReturn(updateUser);
		
		assertThat(this.service.updateUser(id, user)).isEqualTo(updateUser);
		Mockito.verify(this.repository, Mockito.times(1)).findById(id);
		Mockito.verify(this.repository, Mockito.times(1)).save(updateUser);
		
		
	}

	@Test
	void testDeleteUserById() {
		Long id = 10L;
		service.deleteUserById(id);
		verify(repository, times(1)).deleteById(id);
	}


}
