package com.example.demo.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Generates a secure key
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;  // Token expiration time (e.g., 1 hour)

    // Generate JWT token for a given email (or username)
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)  // The email (or username) of the user
                .setIssuedAt(new Date())  // Set the current time as the issue date
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // Expiration time
                .signWith(SECRET_KEY)  // Sign the token with the secure key
                .compact();  // Create and return the final token
    }

    // Extract email from JWT token
    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();  // Return the subject (email)
    }

    // Validate the JWT token (e.g., check expiration)
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);  // If no exception is thrown, the token is valid
            return true;
        } catch (Exception e) {
            return false;  // Token is invalid or expired
        }
    }
}
