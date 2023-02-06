package com.ingenium.ingeniumecommerce.security.config;

import com.ingenium.ingeniumecommerce.user.UserCustomDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days
    @Value("${jwt.secret.key}")
    private String secretKey;
    public String extractUsernameFromToken(final String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(final String token, final UserCustomDetails userCustomDetails) {
        final String username = extractUsernameFromToken(token);
        return (username.equals(userCustomDetails.getUsername())) && !isTokenExpired(token);
    }

    public String generateToken(final UserCustomDetails userCustomDetails) {
        return generateToken(new HashMap<>(), userCustomDetails);
    }
    public String generateToken(final Map<String, Object> extraClaims, final UserCustomDetails userCustomDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userCustomDetails.getUsername())
                .claim("authorities", userCustomDetails.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(final String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(final String token, final Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        final byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
