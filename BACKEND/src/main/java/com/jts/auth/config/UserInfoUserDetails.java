// src/main/java/com/jts/login/config/UserInfoUserDetails.java
package com.jts.auth.config;

// UserDetails personalizado
// src/main/java/com/jts/auth/config/UserInfoUserDetails.java


import com.jts.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@RequiredArgsConstructor
public class UserInfoUserDetails implements UserDetails {

	private final User user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority("USER"));
	}

	@Override public String getPassword() { return user.getPassword(); }
	@Override public String getUsername() { return user.getUsername(); }
	@Override public boolean isAccountNonExpired() { return true; }
	@Override public boolean isAccountNonLocked() { return true; }
	@Override public boolean isCredentialsNonExpired() { return true; }
	@Override public boolean isEnabled() { return true; }
}