package com.warehouse.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.warehouse.dto.UserDto;
import com.warehouse.dto.mapper.MapstructMapper;
import com.warehouse.entity.Role;
import com.warehouse.entity.User;
import com.warehouse.exception.UsernameNotFoundException;
import com.warehouse.repository.UserRepository;

@Service
public class AccountDetailsService implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(AccountDetailsService.class);

	private UserRepository userRepository;
	private RoleService roleService;
	private BCryptPasswordEncoder bcryptEncoder;
	private MapstructMapper mapstructMapper;

	public AccountDetailsService(UserRepository userRepository, RoleService roleService,
			@Lazy BCryptPasswordEncoder bcryptEncoder, MapstructMapper mapstructMapper) {
		this.userRepository = userRepository;
		this.roleService = roleService;
		this.bcryptEncoder = bcryptEncoder;
		this.mapstructMapper = mapstructMapper;
	}

	private static final String ROLE = "ROLE_";

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByName(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("Username, %s, not exist.", username)));

		return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
				getAuthorities(user));
	}

	private Set<SimpleGrantedAuthority> getAuthorities(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(ROLE + role.getName())));
		return authorities;
	}

	public ResponseEntity<String> save(UserDto userDto) {

		if (userRepository.existsUserByName(userDto.getName())) {
			log.error("Username {} is alreday exist", userDto.getName());
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(String.format("Username %s is alreday exist", userDto.getName()));
		}

		User user = mapstructMapper.userDtoToUser(userDto);
		user.setPassword(bcryptEncoder.encode(userDto.getPassword()));

		Role role = roleService.findByName(userDto.getRoleDtos().iterator().next().getName().toString());
		Set<Role> roleSet = new HashSet<>();
		roleSet.add(role);
		user.setRoles(roleSet);

		userRepository.save(user);

		log.info("Username {} is registered", userDto.getName());

		return ResponseEntity.status(HttpStatus.OK).body(String.format("Username %s is registered", userDto.getName()));
	}

}
