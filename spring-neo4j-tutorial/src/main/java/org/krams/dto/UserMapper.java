package org.krams.dto;

import java.util.ArrayList;
import java.util.List;

import org.krams.domain.User;
import org.krams.dto.UserDto;

public class UserMapper {

	public static UserDto map(User user) {
			UserDto dto = new UserDto();
			dto.setId(user.getId());
			dto.setFirstName(user.getFirstName());
			dto.setLastName(user.getLastName());
			dto.setUsername(user.getUsername());
			dto.setRole(user.getRole().getRole());
			return dto;
	}
	
	public static List<UserDto> map(List<User> users) {
		List<UserDto> dtos = new ArrayList<UserDto>();
		for (User user: users) {
			dtos.add(map(user));
		}
		return dtos;
	}
}
