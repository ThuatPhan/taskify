package org.example.taskify.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import org.example.taskify.entity.User;

import java.text.ParseException;

public interface JwtService {
    String generateToken(User user);

    SignedJWT verifyToken(String accessToken, boolean allowExpiredToken) throws ParseException, JOSEException;

    void invalidateToken(String accessToken) throws ParseException, JOSEException;
}
