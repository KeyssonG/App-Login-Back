package com.example.login_auth_api.infra.security;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.login_auth_api.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("login-auth-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt()
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while authenticating");
        }
    }
}