package com.emp.management.service.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
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
    GETTING THE SECRET KEY
    * */
    private Key getSignKey() {
        log.info("Getting the secret key");

        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws MalformedJwtException, SignatureException, ExpiredJwtException {
        log.info("Extracting claim from the token");

        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /*
    EXTRACTING USERNAME FROM THE TOKEN
    **/
    public String extractUsername(String token) throws MalformedJwtException, SignatureException, ExpiredJwtException {
        log.info("Extracting username from the token");

        return extractClaim(token, Claims::getSubject);
    }

    /*
EXTRACTING ALL CLAIMS FROM THE TOKEN
* */
    private Claims extractAllClaims(String token) throws MalformedJwtException, SignatureException, ExpiredJwtException {
        log.info("Extracting all claims from the token");

        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    /*
    GENERATE TOKEN WITH ALL CLAIMS AND USER DETAILS
    * */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        log.info("Generating token with all claims and user details");

        return Jwts.builder()
                .setClaims(extraClaims)
                .signWith(getSignKey())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 hours
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
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
    public Boolean validateToken(String token, UserDetails userDetails){
        log.info("validateToken {} : validating token");

        try {
            boolean isValid = extractUsername(token).equals(userDetails.getUsername())
//                    && !isTokenExpired(token)
                    ;
            log.info("Token is valid : {}", isValid);
            return isValid;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT Token : {}",e.getMessage());
        }catch(ExpiredJwtException e){
            log.error("JWT Token is Expired : {}",e. getMessage());
        }catch(UnsupportedJwtException e){
            log.error("Unsupported JWT :{}", e.getMessage());
        }catch(IllegalArgumentException e){
            log.error("JWT Payload is Empty: {}", e.getMessage());
        }
        return false;
    }


   /* *//*
    EXTRACTING EXPIRATION DATE FROM THE TOKEN
    **//*
    private boolean isTokenExpired(String token) {
        log.info("isTokenExpired() : Extracting expiration date from the token");

        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) {
        log.info("extractExpiration() : Extracting expiration date from the token");

        return extractClaim(token, Claims::getExpiration);
    }*/
}
