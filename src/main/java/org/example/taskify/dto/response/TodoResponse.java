package org.example.taskify.dto.response;

import java.time.Instant;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TodoResponse {
    String id;
    String title;
    String description;
    Set<TagResponse> tags;
    Instant createdAt;
    Instant updatedAt;
}
