package org.example.taskify.service;

import java.text.ParseException;

import org.example.taskify.entity.User;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;

public interface JwtService {
    String generateToken(User user);

    SignedJWT verifyToken(String accessToken, boolean allowExpiredToken) throws ParseException, JOSEException;

    void invalidateToken(String accessToken) throws ParseException, JOSEException;
}
