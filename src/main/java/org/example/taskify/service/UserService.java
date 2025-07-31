package org.example.taskify.service;

import org.example.taskify.dto.request.CreatePasswordRequest;
import org.example.taskify.dto.request.CreateUserRequest;
import org.example.taskify.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);

    void createPassword(CreatePasswordRequest request);

    UserResponse getMyInfo();

    List<UserResponse> getUsers();
}
