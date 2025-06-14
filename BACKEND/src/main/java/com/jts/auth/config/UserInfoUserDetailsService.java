// src/main/java/com/jts/login/config/UserInfoUserDetailsService.java
package com.jts.auth.config;

// UserDetailsService personalizado
// src/main/java/com/jts/auth/config/UserInfoUserDetailsService.java


import com.jts.user.model.User;
import com.jts.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
		return new UserInfoUserDetails(user);
	}
}