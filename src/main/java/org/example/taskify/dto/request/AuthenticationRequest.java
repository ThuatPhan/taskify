package org.example.taskify.dto.request;

import jakarta.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {
    @NotBlank(message = "Username is required")
    String username;

    @NotBlank(message = "Password is required")
    String password;
}
