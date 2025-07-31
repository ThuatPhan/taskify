package org.example.taskify.mapper;

import org.example.taskify.dto.request.RoleRequest;
import org.example.taskify.dto.response.RoleResponse;
import org.example.taskify.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toEntity(RoleRequest request);

    RoleResponse toResponse(Role role);

    List<RoleResponse> toResponseList(List<Role> roles);
}
