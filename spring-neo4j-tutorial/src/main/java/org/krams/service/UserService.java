package org.krams.service;

import java.util.ArrayList;
import java.util.List;
import org.krams.domain.User;
import org.krams.repository.RoleRepository;
import org.krams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public User create(User user) {
		User existingUser = userRepository.findByUsername(user.getUsername());
		
		if (existingUser != null) {
			throw new RuntimeException("Record already exists!");
		}
		
		user.getRole().setUser(user);
		return userRepository.save(user);
	}
	
	public User read(User user) {
		return user;
	}
	
	public List<User> readAll() {
		List<User> users = new ArrayList<User>();
		
		EndResult<User> results = userRepository.findAll();
		for (User r: results) {
			users.add(r);
		}
		
		return users;
	}
	
	public User update(User user) {
		User existingUser = userRepository.findByUsername(user.getUsername());
		
		if (existingUser == null) {
			return null;
		}
		
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.getRole().setRole(user.getRole().getRole());
		
		roleRepository.save(existingUser.getRole());
		return userRepository.save(existingUser);
	}
	
	public Boolean delete(User user) {
		User existingUser = userRepository.findByUsername(user.getUsername());
		
		if (existingUser == null) {
			return false;
		}
		
		userRepository.delete(existingUser);
		return true;
	}
}
