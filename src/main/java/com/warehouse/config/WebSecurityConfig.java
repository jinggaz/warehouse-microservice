package com.warehouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.warehouse.authenticate.JwtAuthenticationFilter;
import com.warehouse.authenticate.UnauthorizedEntryPoint;
import com.warehouse.service.AccountDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	private AccountDetailsService accountDetailsService;
	private UnauthorizedEntryPoint unauthorizedEntryPoint;

	public WebSecurityConfig(AccountDetailsService accountDetailsService,
			UnauthorizedEntryPoint unauthorizedEntryPoint) {
		this.accountDetailsService = accountDetailsService;
		this.unauthorizedEntryPoint = unauthorizedEntryPoint;
	}

	private static final String READ_ONLY = "READ_ONLY";
	private static final String READ_WRITE = "READ_WRITE";

	@Bean
	JwtAuthenticationFilter authenticationTokenFilterBean() {
		return new JwtAuthenticationFilter();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(accountDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	WebSecurityCustomizer webSecurityCustomizer(MvcRequestMatcher.Builder mvc) {
		return web -> web.ignoring().requestMatchers(mvc.pattern("/swagger-ui.html"),
				mvc.pattern("/swagger-resources/**"), mvc.pattern("/swagger-ui/**"), mvc.pattern("/v3/api-docs/**"));
	}

	@Bean
	SecurityFilterChain configure(HttpSecurity httpSecurity, MvcRequestMatcher.Builder mvc) throws Exception {

		httpSecurity.csrf(csrf -> csrf.disable())
				.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedEntryPoint))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> 
					auth.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
						.requestMatchers(mvc.pattern("/account/login")).permitAll()
						.requestMatchers(mvc.pattern("/test/readonly")).hasRole(READ_ONLY)
						.requestMatchers(mvc.pattern("/test/readwright"),
										 mvc.pattern("/account/register"),
										 mvc.pattern("/receive/product"),
										 mvc.pattern("/recieve/package/**")).hasRole(READ_WRITE)
						.requestMatchers(mvc.pattern("/inventory/all"),
										 mvc.pattern("/manufacturer/all")).hasAnyRole(READ_WRITE, READ_ONLY)
						.anyRequest().authenticated());

		httpSecurity.authenticationProvider(authenticationProvider());
		httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
		httpSecurity.headers(headers -> headers.frameOptions(FrameOptionsConfig::disable));

		return httpSecurity.build();
	}

	@Bean
	@Scope("prototype")
	MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector instrospector) {
		return new MvcRequestMatcher.Builder(instrospector);
	}

}
