package org.example.taskify.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.example.taskify.dto.request.CreatePasswordRequest;
import org.example.taskify.dto.request.CreateUserRequest;
import org.example.taskify.dto.response.ApiResponse;
import org.example.taskify.dto.response.UserResponse;
import org.example.taskify.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Tag(name = "User API")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        return ApiResponse.success(201, userService.createUser(request));
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers() {
        return ApiResponse.success(200, userService.getUsers());
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/info")
    public ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.success(200, userService.getMyInfo());
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/create-password")
    @ResponseStatus(HttpStatus.OK)
    public void createPassword(@RequestBody @Valid CreatePasswordRequest request) {
        userService.createPassword(request);
    }
}
