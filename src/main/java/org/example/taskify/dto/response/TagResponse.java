package org.example.taskify.dto.response;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TagResponse implements Serializable {
    String id;
    String name;
    String description;
}
