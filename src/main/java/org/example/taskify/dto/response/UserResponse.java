package org.example.taskify.dto.response;

import java.time.Instant;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

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
    boolean hasPassword = false;
    Set<RoleResponse> roles;
}
