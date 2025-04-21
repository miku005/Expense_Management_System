package com.Expense_Management_System.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
@Service
public class JwtService {

    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry.duration}")
    private int expiryDuration;

    private Algorithm algorithm;

    @PostConstruct
    public void postConstruct( )throws UnsupportedEncodingException {
        algorithm = Algorithm.HMAC256(algorithmKey);
    }
    public String generateToken(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        return JWT.create()
                .withClaim("name", email)
                .withExpiresAt(new Date(System.currentTimeMillis() + expiryDuration))
                .withIssuer(issuer)
                .sign(algorithm);
    }
    public String getEmail(String token){
        DecodedJWT decodedJWT = JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token);
        return decodedJWT.getClaim("name").asString();
    }
}
