package org.example.taskify.dto.request;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRequest {
    @NotBlank(message = "Name is required")
    String name;

    String description;

    Set<String> permissions;
}
