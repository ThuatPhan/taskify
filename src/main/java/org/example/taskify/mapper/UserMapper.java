package org.example.taskify.mapper;

import org.example.taskify.dto.request.CreateUserRequest;
import org.example.taskify.dto.request.UpdateUserRequest;
import org.example.taskify.dto.response.UserResponse;
import org.example.taskify.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(CreateUserRequest request);

    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> users);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget User user, UpdateUserRequest request);
}
