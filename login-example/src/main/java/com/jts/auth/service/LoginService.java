package com.jts.auth.service;

import com.jts.user.service.UserService;
import com.jts.auth.dto.SignupRequest;
import com.jts.auth.dto.SignupResponse;
import com.jts.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	@Autowired
	private UserService userService;

	public SignupResponse doRegister(SignupRequest request) {
		SignupResponse response = new SignupResponse();

		if (userService.findByUsername(request.getUsername()).isPresent()) {
			response.setResponse("User details already exist");
			return response;
		}

		User user = new User();
		user.setName(request.getName());
		user.setUsername(request.getUsername());
		user.setPassword(request.getPassword());
		user.setAddress(request.getAddress());
		user.setMobileNo(request.getMobileno());

		User saved = userService.save(user);
		response.setResponse("User created with user name: " + saved.getUsername());
		return response;
	}
}
