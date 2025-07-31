package org.example.taskify.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.taskify.dto.request.PermissionRequest;
import org.example.taskify.dto.response.PermissionResponse;
import org.example.taskify.entity.Permission;
import org.example.taskify.exception.AppException;
import org.example.taskify.exception.ErrorCode;
import org.example.taskify.mapper.PermissionMapper;
import org.example.taskify.repository.PermissionRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionServiceImpl implements PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public PermissionResponse createPermission(PermissionRequest request) {
        boolean isPermissionExists = permissionRepository.existsByName(request.getName());
        if (isPermissionExists) {
            throw new AppException(ErrorCode.PERMISSION_ALREADY_EXISTS);
        }

        Permission newPermission = permissionMapper.toEntity(request);

        return permissionMapper.toResponse(permissionRepository.save(newPermission));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<PermissionResponse> getPermissions() {
        return permissionMapper.toResponseList(permissionRepository.findAll());
    }
}
