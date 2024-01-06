package com.warehouse.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse.authenticate.JwtTokenProvider;
import com.warehouse.dto.AuthToken;
import com.warehouse.dto.LoginUser;
import com.warehouse.dto.UserDto;
import com.warehouse.service.AccountDetailsService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/account")
public class AccountController {

	private static final Logger log = LogManager.getLogger(AccountController.class);

	private AuthenticationManager authenticationManager;
	private AccountDetailsService accountDetailsService;
	private JwtTokenProvider jwtTokenProvider;

	public AccountController(AuthenticationManager authenticationManager, AccountDetailsService accountDetailsService,
			JwtTokenProvider jwtTokenProvider) {
		this.authenticationManager = authenticationManager;
		this.accountDetailsService = accountDetailsService;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@PostMapping("/register")
	@Operation(summary = "Add new user")
	public ResponseEntity<String> saveUser(@RequestBody UserDto user) {

		return accountDetailsService.save(user);
	}

	@PostMapping("/login")
	@Operation(summary = "Login & get JWT token")
	public ResponseEntity<AuthToken> loginToWarehouse(@RequestBody LoginUser loginUser) throws AuthenticationException {

		log.info("Login to Warehouse.");

		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = jwtTokenProvider.generateToken(authentication);

		return ResponseEntity.ok(new AuthToken(token));
	}

}
