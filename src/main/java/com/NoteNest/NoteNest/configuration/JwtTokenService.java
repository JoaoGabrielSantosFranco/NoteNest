package com.NoteNest.NoteNest.configuration;

import com.NoteNest.NoteNest.security.UserDetailsImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class JwtTokenService {

    private final Algorithm algorithm;
    private static final String ISSUER = "pizzurg-api";
    private final ZoneId zoneId;
    private final long expirationHours;

    public JwtTokenService(@Value("${app.secret.key}") String secretKey,
                           @Value("${app.jwt.expiration-hours}") long expirationHours,
                           @Value("${app.jwt.timezone}") String timezone) {
        if (secretKey == null || secretKey.isBlank()) {
            throw new IllegalArgumentException("JWT Secret Key cannot be null or empty.");
        }
        this.algorithm = Algorithm.HMAC256(secretKey);
        this.zoneId = ZoneId.of(timezone);
        this.expirationHours = expirationHours;
    }

    public String generateToken(UserDetailsImpl user) {
        try {
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(creationDate())
                    .withExpiresAt(expirationDate())
                    .withSubject(user.getUsername())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Erro ao gerar token.", exception);
        }
    }

    public String getSubjectFromToken(String token) {
        try {
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Token inválido ou expirado.");
        }
    }

    private Instant creationDate() {
        return ZonedDateTime.now(zoneId).toInstant();
    }

    private Instant expirationDate() {
        return ZonedDateTime.now(zoneId).plusHours(expirationHours).toInstant();
    }
}
