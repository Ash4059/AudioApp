package com.TuneWave.AudioApp.Service.Implementation;

import com.TuneWave.AudioApp.Entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    @Value("${security.jwtSecret}")
    private String secretKey;

    // Expiration time set to 60 Minute
    final long expirationTime = 60 * 60 * 1000;

    // Extract expiry date from token
    public Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }

    // Extract username from token
    public String extractUserName(String token){
        return extractClaims(token, Claims::getSubject);
    }

    // Validate token with User
    public Boolean ValidateToken(String token, String user){
        final String userName = extractUserName(token);
        return user.equals(userName) && !isTokenExpired(token);
    }

    // Check token is expired
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    // generic method used to extract a specific claim from the JWT token’s claims.
    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Get all claims
    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String GenerateToken(User user){
        Map<String, Object> claims = new HashMap<>();
        return buildToken(claims, user);
    }

    private String buildToken(Map<String, Object> extraClaims,
                              User user){
        return Jwts
                .builder()
                .claims()
                .add(extraClaims)
                .subject(user.getUserName())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .and()
                .signWith(getSignKey())
                .compact();
    }

    private Key getSignKey(){
        byte [] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
