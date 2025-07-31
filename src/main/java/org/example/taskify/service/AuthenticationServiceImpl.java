package org.example.taskify.service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.example.taskify.constant.PredefinedRole;
import org.example.taskify.dto.request.*;
import org.example.taskify.dto.response.AuthenticationResponse;
import org.example.taskify.dto.response.ExchangeTokenResponse;
import org.example.taskify.dto.response.IntrospectResponse;
import org.example.taskify.dto.response.UserInfoResponse;
import org.example.taskify.entity.InvalidatedToken;
import org.example.taskify.entity.Role;
import org.example.taskify.entity.User;
import org.example.taskify.exception.AppException;
import org.example.taskify.exception.ErrorCode;
import org.example.taskify.repository.InvalidatedTokenRepository;
import org.example.taskify.repository.RoleRepository;
import org.example.taskify.repository.UserRepository;
import org.example.taskify.repository.httpClient.OutboundIdentityClient;
import org.example.taskify.repository.httpClient.OutboundUserClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
    InvalidatedTokenRepository invalidatedTokenRepository;
    OutboundIdentityClient outboundIdentityClient;
    OutboundUserClient outboundUserClient;
    JwtService jwtService;
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${outbound.identity.client-id}")
    String CLIENT_ID;

    @NonFinal
    @Value("${outbound.identity.client-secret}")
    String CLIENT_SECRET;

    @NonFinal
    @Value("${outbound.identity.redirect-uri}")
    String REDIRECT_URI;

    @NonFinal
    @Value("${outbound.identity.grant_type}")
    String GRANT_TYPE;

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException {
        boolean isValid = true;

        try {
            jwtService.verifyToken(request.getAccessToken(), false);
        } catch (AppException e) {
            isValid = false;
        }

        return IntrospectResponse.builder().isValid(isValid).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User userToAuthenticate = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        boolean isPasswordMatch = passwordEncoder.matches(request.getPassword(), userToAuthenticate.getPassword());

        if (!isPasswordMatch) {
            throw new AppException(ErrorCode.INVALID_CREDENTIALS);
        }

        String accessToken = jwtService.generateToken(userToAuthenticate);

        return AuthenticationResponse.builder().accessToken(accessToken).build();
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException {
        SignedJWT signedJWT = jwtService.verifyToken(request.getAccessToken(), true);

        String jwtId = signedJWT.getJWTClaimsSet().getJWTID();
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken =
                InvalidatedToken.builder().id(jwtId).expiryTime(expiryTime).build();
        invalidatedTokenRepository.save(invalidatedToken);

        String username = signedJWT.getJWTClaimsSet().getSubject();

        User user =
                userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        String newAccessToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().accessToken(newAccessToken).build();
    }

    @Override
    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        SignedJWT signedJWT = jwtService.verifyToken(request.getAccessToken(), false);

        String jwtId = signedJWT.getJWTClaimsSet().getJWTID();
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken =
                InvalidatedToken.builder().id(jwtId).expiryTime(expiryTime).build();
        invalidatedTokenRepository.save(invalidatedToken);
    }

    @Override
    public AuthenticationResponse outboundAuthenticate(String code) {
        ExchangeTokenRequest request = ExchangeTokenRequest.builder()
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .code(code)
                .redirectUri(REDIRECT_URI)
                .grantType(GRANT_TYPE)
                .build();

        ExchangeTokenResponse response = outboundIdentityClient.exchangeToken(request);

        UserInfoResponse userInfo = outboundUserClient.getUserInfo("json", response.getAccessToken());

        User newUser = onboardUser(userInfo);

        String accessToken = jwtService.generateToken(newUser);

        return AuthenticationResponse.builder().accessToken(accessToken).build();
    }

    private User onboardUser(UserInfoResponse userInfo) {
        Set<Role> roles = new HashSet<>();
        roleRepository.findByName(PredefinedRole.USER).ifPresent(roles::add);

        return userRepository.findByUsername(userInfo.getEmail()).orElseGet(() -> {
            String firstname = userInfo.getGivenName();
            String lastname = userInfo.getFamilyName();

            return userRepository.save(User.builder()
                    .username(userInfo.getEmail())
                    .firstName(firstname)
                    .lastName(lastname)
                    .email(userInfo.getEmail())
                    .avatar(userInfo.getPicture())
                    .roles(roles)
                    .build());
        });
    }
}
