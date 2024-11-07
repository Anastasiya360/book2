package com.example.book2.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import static org.springframework.security.config.Elements.JWT;

@Component
public class JwtTokenUtil {

    public static final int ACCESS_TOKEN_VALIDITY = 10;

    @Value("${jwt.secret}")
    public String secret;

    public Long getUserIdFromToken(String token) {
        return Long.valueOf((Integer) getAllClaimsFromToken(token).get("uid"));
    }

    @SuppressWarnings("unchecked")
    public Collection<String> getPermsFromToken(String token) {
        return (Collection<String>) getAllClaimsFromToken(token).get("perms");
    }

    public Claims getAllClaimsFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateToken(long userId, String login, String issuer, Set<String> perms) {
        Claims claims = Jwts.claims()
                .subject(login)
                .issuer(issuer)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY * 60 * 1000))
                .add("uid", userId)
                .add("perms", (perms != null) ? perms : Collections.emptySet())
                .build();

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .header().type(JWT).and()
                .claims(claims)
                .signWith(key)
                .compact();
    }
}