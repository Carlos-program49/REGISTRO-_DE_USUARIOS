// src/main/java/com/jts/login/config/JwtAuthFilter.java
package com.jts.auth.config;

// Filtro de autenticación JWT
// src/main/java/com/jts/auth/config/JwtAuthFilter.java


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	private final JWTService jwtService;
	private final UserInfoUserDetailsService userDetailsService;

	public JwtAuthFilter(JWTService jwtService, UserInfoUserDetailsService uds) {
		this.jwtService = jwtService;
		this.userDetailsService = uds;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req,
									HttpServletResponse res,
									FilterChain chain)
			throws ServletException, IOException {
		String authHeader = req.getHeader("Authorization");
		String token = null, username = null;

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			username = jwtService.extractUsername(token);
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			var userDetails = userDetailsService.loadUserByUsername(username);
			if (jwtService.validateToken(token, userDetails)) {
				var authToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}

		chain.doFilter(req, res);
	}
}