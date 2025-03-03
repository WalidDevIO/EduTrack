package fr.elite.authapi.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import fr.elite.authapi.exceptions.BannedTokenException;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Component
public class JwtUtil {
    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final ConcurrentHashMap<String, Boolean> invalidatedTokens = new ConcurrentHashMap<>();

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration)) // 1h
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token) {
        if (invalidatedTokens.containsKey(token))
            throw new BannedTokenException("Invalidated token");
        
        if(getExpirationDate(token).before(new Date())) {
            return false;
        }

        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public void invalidateToken(String token) {
        invalidatedTokens.put(token, true);
    }

    public String getUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date getExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
            .parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    private <T> T extractClaim(String token, Function<Claims, T> extractor) {
        return extractor.apply(extractAllClaims(token));
    }
    
}
