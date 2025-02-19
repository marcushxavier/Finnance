package br.com.finnance.security.services;

import br.com.finnance.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    //    @Value("${api.security.token.secret}")
    private String secret = "973WESDRDCVUYIug75d7f8d6ix586YTUORD";

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            Instant tokenExpirationDate = LocalDateTime.now()
                    .plusDays(10)
                    .toInstant(ZoneOffset.of("-03:00"));

            return JWT.create()
                    .withIssuer("finnance")
                    .withSubject(user.getEmail())
                    .withExpiresAt(tokenExpirationDate)
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("erro na criação do token", e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("finnance")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }

}
