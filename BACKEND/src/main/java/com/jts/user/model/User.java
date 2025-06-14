package com.jts.user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String name;

	private String address;

	@Column(name = "mobile_no")
	private String mobileNo;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;
}