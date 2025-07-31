package org.example.taskify.dto.request;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TodoCreationRequest {
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    @NotBlank(message = "Title is required")
    String title;

    @Size(min = 1, max = 500, message = "Description must be between 1 and 500 characters")
    String description;

    @Size(min = 1, max = 5, message = "You can add as least 1 and up to 5 tags")
    Set<String> tags;
}
