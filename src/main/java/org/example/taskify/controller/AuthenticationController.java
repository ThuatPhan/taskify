package org.example.taskify.controller;

import java.text.ParseException;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.example.taskify.dto.request.AuthenticationRequest;
import org.example.taskify.dto.request.IntrospectRequest;
import org.example.taskify.dto.request.LogoutRequest;
import org.example.taskify.dto.request.RefreshTokenRequest;
import org.example.taskify.dto.response.ApiResponse;
import org.example.taskify.dto.response.AuthenticationResponse;
import org.example.taskify.dto.response.IntrospectResponse;
import org.example.taskify.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Tag(name = "Auth API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        return ApiResponse.success(200, authenticationService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public ApiResponse<AuthenticationResponse> refreshToken(@RequestBody @Valid RefreshTokenRequest request)
            throws ParseException, JOSEException {
        return ApiResponse.success(200, authenticationService.refreshToken(request));
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody @Valid IntrospectRequest request)
            throws ParseException, JOSEException {
        return ApiResponse.success(200, authenticationService.introspect(request));
    }

    @PostMapping("/logout")
    public void logout(@RequestBody @Valid LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
    }

    @PostMapping("/outbound/authentication")
    public ApiResponse<AuthenticationResponse> outboundAuthentication(@RequestParam String code) {
        return ApiResponse.success(200, authenticationService.outboundAuthenticate(code));
    }
}
