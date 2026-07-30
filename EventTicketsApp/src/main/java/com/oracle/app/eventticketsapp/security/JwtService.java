package com.oracle.app.eventticketsapp.security;

import com.oracle.app.eventticketsapp.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;



@Service
public class JwtService {

    private static final String SECRET_KEY =
            "my-super-secret-key-for-event-tickets-application-2026";

    private static final long EXPIRATION_TIME =
            1000 * 60 * 60; // 1 hour


    private SecretKey getSigningKey() {

        byte[] keyBytes =
                Decoders.BASE64.decode(
                        java.util.Base64
                                .getEncoder()
                                .encodeToString(
                                        SECRET_KEY.getBytes()
                                )
                );

        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String generateToken(User user) {

        return Jwts.builder()

                // User ID stored as JWT subject
                .subject(user.getId())

                // Additional information
                .claim("email", user.getEmail())
                .claim("role", user.getRole().name())

                // Token dates
                .issuedAt(new Date())

                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + EXPIRATION_TIME
                        )
                )

                // Sign token
                .signWith(getSigningKey())

                .compact();
    }


    public String extractUserId(String token) {

        return extractAllClaims(token)
                .getSubject();
    }


    public boolean isTokenValid(
            String token,
            User user
    ) {

        String userId =
                extractUserId(token);

        return userId.equals(user.getId())
                && !isTokenExpired(token);
    }


    private boolean isTokenExpired(String token) {

        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }


    private Claims extractAllClaims(String token) {

        return Jwts.parser()

                .verifyWith(getSigningKey())

                .build()

                .parseSignedClaims(token)

                .getPayload();
    }
}
