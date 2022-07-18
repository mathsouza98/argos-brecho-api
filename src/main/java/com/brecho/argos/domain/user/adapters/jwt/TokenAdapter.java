package com.brecho.argos.domain.user.adapters.jwt;

import com.brecho.argos.domain.user.core.enums.Role;
import com.brecho.argos.domain.user.core.models.TokenData;
import com.brecho.argos.domain.user.core.models.User;
import com.brecho.argos.domain.user.core.ports.TokenPort;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenAdapter implements TokenPort {
    @Value("jwt.secret")
    private String secret;

    @Value("jwt.expiration.seconds")
    private int expirationSeconds;

    @Override
    public String generateToken(User user) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + (expirationSeconds * 100L));

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("roles", user.getRoles());

        log.info("Token generated!");
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getName())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public TokenData getTokenData(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        Jws<Claims> jws = null;
        try {
            jws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
        } catch (JwtException ex) {
            log.error("JWT cannot be trusted!");
        }

        assert jws != null;
        return TokenData.builder()
                .userId(String.valueOf(jws.getBody().get("userId")))
                .role(Role.valueOf((String) jws.getBody().get("role")))
                .build();
    }

    @Override
    public String refreshToken(String token) {
        return null;
    }
}