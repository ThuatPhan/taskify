package org.example.taskify.service;

import java.util.HashSet;
import java.util.List;

import org.example.taskify.dto.request.RoleRequest;
import org.example.taskify.dto.response.RoleResponse;
import org.example.taskify.entity.Permission;
import org.example.taskify.entity.Role;
import org.example.taskify.exception.AppException;
import org.example.taskify.exception.ErrorCode;
import org.example.taskify.mapper.RoleMapper;
import org.example.taskify.repository.PermissionRepository;
import org.example.taskify.repository.RoleRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {
    PermissionRepository permissionRepository;
    RoleRepository roleRepository;
    RoleMapper roleMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public RoleResponse createRole(RoleRequest request) {
        boolean isRoleExists = roleRepository.existsByName(request.getName());
        if (isRoleExists) {
            throw new AppException(ErrorCode.ROLE_ALREADY_EXISTS);
        }

        Role newRole = roleMapper.toEntity(request);

        List<Permission> permissions = permissionRepository.findAllByNameIn(request.getPermissions());
        newRole.setPermissions(new HashSet<>(permissions));

        return roleMapper.toResponse(roleRepository.save(newRole));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<RoleResponse> getRoles() {
        return roleMapper.toResponseList(roleRepository.findAll());
    }
}
