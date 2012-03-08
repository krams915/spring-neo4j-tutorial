package org.krams.service;

import org.krams.domain.Role;
import org.krams.domain.User;
import org.krams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Service for initializing MongoDB with sample data using {@link MongoTemplate}
 */
public class InitNeo4jService {
	
	@Autowired
	private UserRepository userRepository;

	public void init() {
		if (userRepository.findByUsername("john") != null) {
			userRepository.delete(userRepository.findByUsername("john"));
		}
		
		if (userRepository.findByUsername("jane") != null) {
			userRepository.delete(userRepository.findByUsername("jane"));
		}

		// Create new records
		User john = new User();
		john.setFirstName("John");
		john.setLastName("Smith");
		john.setPassword("21232f297a57a5a743894a0e4a801fc3");
		john.setRole(new Role(1));
		john.setUsername("john");
		
		User jane = new User();
		jane.setFirstName("Jane");
		jane.setLastName("Adams");
		jane.setPassword("ee11cbb19052e40b07aac0ca060c23ee");
		jane.setRole(new Role(2));
		jane.setUsername("jane");
		
		john.getRole().setUser(john);
		jane.getRole().setUser(jane);

		userRepository.save(john);
		userRepository.save(jane);
		
		userRepository.findByUsername("john").getRole().getRole();
	}
}
