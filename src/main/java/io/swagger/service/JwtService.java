package io.swagger.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;
    private Algorithm algorithm = Algorithm.HMAC512(secret);

    public String generateToken(String username) {
        return JWT.create()
            .withClaim("username", username)
            .sign(algorithm);
    }

    public Map<String, Claim> verifyToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer("Simple Solution")
            .build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getClaims();
    }
}
