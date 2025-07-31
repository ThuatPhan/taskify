package org.example.taskify.dto.request;

import jakarta.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TodoUpdateRequest {
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    String title;

    @Size(min = 1, max = 500, message = "Description must be between 1 and 500 characters")
    String description;

    @Size(max = 5, message = "You can add up to 5 tags")
    Set<String> tags;
}
