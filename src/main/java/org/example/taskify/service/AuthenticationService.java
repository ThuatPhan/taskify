package org.example.taskify.service;

import java.text.ParseException;

import org.example.taskify.dto.request.AuthenticationRequest;
import org.example.taskify.dto.request.IntrospectRequest;
import org.example.taskify.dto.request.LogoutRequest;
import org.example.taskify.dto.request.RefreshTokenRequest;
import org.example.taskify.dto.response.AuthenticationResponse;
import org.example.taskify.dto.response.IntrospectResponse;

import com.nimbusds.jose.JOSEException;

public interface AuthenticationService {
    IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException;

    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException;

    void logout(LogoutRequest request) throws ParseException, JOSEException;

    AuthenticationResponse outboundAuthenticate(String code);
}
