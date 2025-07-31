package org.example.taskify.mapper;

import java.util.List;

import org.example.taskify.dto.request.PermissionRequest;
import org.example.taskify.dto.response.PermissionResponse;
import org.example.taskify.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PermissionMapper {
    Permission toEntity(PermissionRequest request);

    PermissionResponse toResponse(Permission permission);

    List<PermissionResponse> toResponseList(List<Permission> permissions);
}
