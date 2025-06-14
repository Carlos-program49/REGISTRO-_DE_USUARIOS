package com.jts.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignupRequest {
	private String name;
	private String username;
	private String password;
	private String address;
	private String mobileno;
}
