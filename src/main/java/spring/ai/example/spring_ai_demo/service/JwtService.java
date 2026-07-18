package spring.ai.example.spring_ai_demo.service;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import spring.ai.example.spring_ai_demo.entity.User;

@Service
public class JwtService {
	@Value("${app.security.secret-key}")
	private String secretKey;

	public String generateToken(User user) {
		Map<String,Object> claims = new HashMap<>();
		
		return Jwts
		.builder()
		.claims()
		.add(claims)
		.subject(user.getUserName())
		.issuer("")
		.issuedAt(new Date(System.currentTimeMillis()))
		.expiration(new Date(System.currentTimeMillis()+60*10*1000))
		.and()
		.signWith(generateKey())
		.compact();
		
	}
	private SecretKey generateKey() {
		// TODO Auto-generated method stub
		byte[] decode = Base64.getDecoder().decode(getSecretKey());
		return Keys.hmacShaKeyFor(decode);
	}
	public String getSecretKey(){
		return this.secretKey;
	}
	public String extractUserName(String token) {
		// TODO Auto-generated method stub

		return extractClaims(token,Claims::getSubject);
	}
	private <T> T extractClaims(String token, Function<Claims,T> claimsResolver) {
		// TODO Auto-generated method stub
		Claims claims =extractClaims(token);
		return claimsResolver.apply(claims);
	}
	private Claims extractClaims(String token) {
		// TODO Auto-generated method stub

		return Jwts
		.parser()
		.verifyWith(generateKey())
		.build()
		.parseSignedClaims(token)
		.getPayload();
	}
	public boolean isTokenValid(String jwt, UserDetails userDetails) {
		// TODO Auto-generated method stub
		final String userName =extractUserName(jwt);
		return  (userName.equals(userDetails.getUsername())&& !isTokenExpited(jwt));
	}
	private boolean isTokenExpited(String jwt) {
		// TODO Auto-generated method stub
		return extractExpiration(jwt).before(new Date());
	}
	private Date extractExpiration(String jwt) {
		// TODO Auto-generated method stub
		return extractClaims(jwt, Claims::getExpiration);
	}

}
