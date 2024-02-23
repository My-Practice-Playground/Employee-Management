package com.emp.management.service.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class JwtService {

    @Value("${JWT_SECRET_KEY}")
    private String SECRET_KEY;

/*
EXTRACTING ALL CLAIMS FROM THE TOKEN
* */
    private Claims extractAllClaims(String token) {
        log.info("Extracting all claims from the token");

        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

/*
GETTING THE SECRET KEY
* */
    private Key getSignKey() {
        log.info("Getting the secret key");

        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        log.info("Extracting claim from the token");

        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

/*
EXTRACTING USERNAME FROM THE TOKEN
**/
    public String extractUsername(String token) {
        log.info("Extracting username from the token");

        return extractClaim(token, Claims::getSubject);
    }

/*
GENERATE TOKEN WITH ALL CLAIMS AND USER DETAILS
* */
    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails) {
        log.info("Generating token with all claims and user details");

        return Jwts.builder()
                .setClaims(extraClaims)
                .signWith(getSignKey())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) //10 hours
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

/*
GENERATE TOKEN WITH USER DETAILS
**/
    public String generateToken(UserDetails userDetails) {
        log.info("generateToken(UserDetails userDetails) : Generating token with user details");

        return generateToken(new HashMap<>(), userDetails);
    }

/*
VALIDATE TOKEN
**/
    public Boolean validateToken(String token, UserDetails userDetails) {
        log.info("validateToken() : Validating token");

        final String username = extractUsername(token);
        log.info("validateToken() : Username : {}", username);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

/*
EXTRACTING EXPIRATION DATE FROM THE TOKEN
**/
    private boolean isTokenExpired(String token) {
        log.info("isTokenExpired() : Extracting expiration date from the token");

        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) {
        log.info("extractExpiration() : Extracting expiration date from the token");

        return extractClaim(token, Claims::getExpiration);
    }
}
