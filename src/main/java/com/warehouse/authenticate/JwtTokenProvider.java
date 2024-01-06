package com.warehouse.authenticate;

import java.io.Serializable;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider implements Serializable {

	private static final long serialVersionUID = -1557831613847041092L;

	private static final Logger log = LogManager.getLogger(JwtTokenProvider.class);

	@Value("${app.jwt.expiration-milliseconds}")
	private long jwtExpirationDate;

	@Value("${app.jwt.authorities.key}")
	public String authoritiesKey;

	@Value("${app.jwt.secret}")
	public String secretKey;

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();
	}

	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(Authentication authentication) {

		log.info("Generate JWT token.");

		String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		log.info("Authentication is successful.");

		return Jwts.builder().setSubject(authentication.getName())
				.claim(authoritiesKey, authorities)
				.setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + jwtExpirationDate))
				.signWith(key()).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public UsernamePasswordAuthenticationToken getAuthenticationToken(final String token,
			final UserDetails userDetails) {

		final Claims claims = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();

		final Collection<? extends GrantedAuthority> authorities = Arrays
				.stream(claims.get(authoritiesKey).toString().split(",")).map(SimpleGrantedAuthority::new).toList();

		return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
	}

}
