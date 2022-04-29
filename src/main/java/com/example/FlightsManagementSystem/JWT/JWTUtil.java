package com.example.FlightsManagementSystem.JWT;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
//import com.example.demo.BusniessLogics.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JWTUtil {


    @Value("${jwt_secret}")
    private String secret;

    public String generateToken(String username, long id, String userRole) throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
                .withSubject("User Details")
                .withClaim("id",id)
                .withClaim("username", username)
                .withClaim("userRole", userRole)
                .withIssuedAt(new Date())
                .withIssuer("YOUR APPLICATION/PROJECT/COMPANY NAME")
                .sign(Algorithm.HMAC256(secret));
    }


    public List<String> validateTokenAndRetrieveSubject(String token)throws JWTVerificationException
    {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User Details")
                .withIssuer("YOUR APPLICATION/PROJECT/COMPANY NAME")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        ArrayList<String> credentials = new ArrayList<>();
        credentials.add(jwt.getClaim("username").asString());
        credentials.add(jwt.getClaim("id").asString());
        credentials.add(jwt.getClaim("userRole").asString());

////        var list =List.of(jwt.getClaim("id").asString(),
//                jwt.getClaim("username").asString(),
//                jwt.getClaim("userRole").asString());
        return credentials;
    }

}