package com.example.demoWeb.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //token -> get all - claims
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey()) //set key
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);//all-claims
        return claimsResolver.apply(claims);//get target claim
    }

    //got username from token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject); //subject-username // expiration - date
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)//set usernam
                .setIssuedAt(new Date(System.currentTimeMillis())) //from at
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) //to end at
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) //sign with key
                .compact(); //return jwts string
    }

    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) //to end at
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) //sign with key
                .compact();
    }

    //get end-date
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);

    }

    //isExpired?
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //is valid ?
    public boolean isTokenValid(String token, String username) {
        final String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(username)) && !isTokenExpired(token); //match username and expired
    }
}
