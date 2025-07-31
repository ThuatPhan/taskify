package org.example.taskify.controller;

import java.util.List;

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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        return ApiResponse.success(201, userService.createUser(request));
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers() {
        return ApiResponse.success(200, userService.getUsers());
    }

    @GetMapping("/info")
    public ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.success(200, userService.getMyInfo());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/create-password")
    public void createPassword(@RequestBody @Valid CreatePasswordRequest request) {
        userService.createPassword(request);
    }
}
