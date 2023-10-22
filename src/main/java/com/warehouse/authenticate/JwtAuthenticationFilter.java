package com.warehouse.authenticate;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.warehouse.service.AccountDetailsService;

import io.jsonwebtoken.ExpiredJwtException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
    @Autowired
	private AccountDetailsService accountDetailsService;

	@Value("${app.jwt.header.string}")
	public String headerString;

	@Value("${app.jwt.token.prefix}")
	public String tokenPrefix;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader(headerString);
		String username = null;
		String authToken = null;

		if (header != null && header.startsWith(tokenPrefix)) {
			authToken = header.replace(tokenPrefix, "");

			try {
				username = jwtTokenProvider.getUsernameFromToken(authToken);
			} catch (IllegalArgumentException e) {
				log.error("An error occurred while fetching Username from Token", e);
			} catch (ExpiredJwtException e) {
				log.warn("The token has expired", e);
			} catch (Exception e) {
				log.error("Authentication Failed. Username or Password not valid.");
			}
		} else {
			log.warn("Couldn't find bearer string, header will be ignored");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = accountDetailsService.loadUserByUsername(username);

			if (Boolean.TRUE.equals(jwtTokenProvider.validateToken(authToken, userDetails))) {
				UsernamePasswordAuthenticationToken authentication = jwtTokenProvider.getAuthenticationToken(authToken, userDetails);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				log.info("authenticated user {}, setting security", username);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		filterChain.doFilter(request, response);
	}

}
