package org.example.taskify.mapper;

import java.util.List;

import org.example.taskify.dto.request.TodoCreationRequest;
import org.example.taskify.dto.request.TodoUpdateRequest;
import org.example.taskify.dto.response.TodoResponse;
import org.example.taskify.entity.Todo;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TodoMapper {
    @Mapping(target = "tags", ignore = true)
    Todo toEntity(TodoCreationRequest request);

    TodoResponse toResponse(Todo todo);

    @Mapping(target = "tags", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Todo todo, TodoUpdateRequest request);

    List<TodoResponse> toResponseList(List<Todo> todos);
}
