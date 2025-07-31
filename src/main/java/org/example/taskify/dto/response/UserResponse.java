package org.example.taskify.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String email;
    String firstName;
    String lastName;
    String avatar;
    Instant createdAt;
    Instant updatedAt;
    boolean hasPassword;
    Set<RoleResponse> roles;
}
