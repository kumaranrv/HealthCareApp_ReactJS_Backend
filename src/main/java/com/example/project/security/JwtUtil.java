package com.example.project.security;

import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	@Value("${jwt.secret}")
	private String SECRET;
	@Value("${jwt.token.validity}")
	private Long VALIDITY;

	public String generateToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + VALIDITY))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
	}

	public String getUsernameFromToken(String token) {
		Claims claims = getAllClaimsFromToken(token);
		return getClaimFromToken(claims, Claims::getSubject);
	}

	private <T> T getClaimFromToken(Claims claims, Function<Claims, T> claimsResolver) {
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
			return true;
		} catch (JwtException je) {
			System.out.println(je.toString());
			return false;
		}
	}

}