// src/main/java/com/jts/login/controller/LoginController.java
package com.jts.auth.rest;

// Controlador Login
// src/main/java/com/jts/auth/rest/LoginController.java


import com.jts.auth.config.JWTService;
import com.jts.auth.dto.*;
import com.jts.auth.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTService jwtService;

	@Autowired
	private LoginService loginService;

	@PostMapping("/doLogin")
	public ResponseEntity<LoginResponse> doLogin(@RequestBody LoginRequest request) {
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
		);
		LoginResponse response = new LoginResponse();
		if (auth.isAuthenticated()) {
			response.setToken(jwtService.generateToken(request.getUsername()));
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/dashboard")
	public ResponseEntity<DashboardResponse> dashboard() {
		DashboardResponse resp = new DashboardResponse();
		resp.setResponse("Success");
		return ResponseEntity.ok(resp);
	}

	@PostMapping("/doRegister")
	public ResponseEntity<SignupResponse> doRegister(@RequestBody SignupRequest request) {
		return new ResponseEntity<>(loginService.doRegister(request), HttpStatus.CREATED);
	}
}