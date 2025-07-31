package org.example.taskify.service;

import java.util.List;

import org.example.taskify.dto.request.PermissionRequest;
import org.example.taskify.dto.response.PermissionResponse;

public interface PermissionService {
    PermissionResponse createPermission(PermissionRequest request);

    List<PermissionResponse> getPermissions();
}
