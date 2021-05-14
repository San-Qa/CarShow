package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;
import java.util.Optional;

@Service
public class UserService{

	
	private UserRepository userepo;
	
	public UserService(UserRepository userepo) {
		super();
		this.userepo = userepo;
	} 
 
	public User createUser(User user) {
		User create = this.userepo.save(user);
		return create;
	}

	
	public User getUserById(Long id) {
		Optional<User>optionalUser = this.userepo.findById(id);
		return optionalUser.get();
	}

	
	public List<User> getUsers() {
		return this.userepo.findAll();
	}

	
	public boolean deleteUserById(Long id) {
		this.userepo.deleteById(id);
		return !this.userepo.existsById(id);
	}

	
	public User updateUser(Long id, User user) {
		Optional<User>optionalUser = this.userepo.findById(id);
		User ifExists = optionalUser.get();
		
		ifExists.setName(user.getName());
		ifExists.setModel(user.getModel());
		
		User update = this.userepo.save(ifExists);
		return update;
	}

}
