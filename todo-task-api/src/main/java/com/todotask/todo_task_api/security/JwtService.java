package com.todotask.todo_task_api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.todotask.todo_task_api.exception.BusinessException;
import com.todotask.todo_task_api.model.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;


    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return  JWT.create()
                    .withIssuer("auth0")
                    .withSubject(user.getEmail())
                    .withClaim("role", user.getRole().name())
                    .withExpiresAt(Instant.now().plus(2, ChronoUnit.HOURS))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException("Erro ao gerar o token jwt", exception);
        }
    }



    public DecodedJWT validateToken(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build()
                    .verify(tokenJWT);

        } catch (TokenExpiredException exception) {
            throw new BusinessException("Token expirado");

        } catch (JWTVerificationException exception) {
            throw new BusinessException("Token inválido");
        }
    }
}
