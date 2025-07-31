package org.example.taskify.dto.response;

import java.io.Serializable;
import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponse<T> implements Serializable {
    boolean hasNext;
    List<T> results;

    public static <T> PageResponse<T> of(boolean hasNext, List<T> result) {
        return PageResponse.<T>builder().hasNext(hasNext).results(result).build();
    }
}
