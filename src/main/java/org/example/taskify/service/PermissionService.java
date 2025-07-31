package org.example.taskify.service;

import org.example.taskify.dto.request.PermissionRequest;
import org.example.taskify.dto.response.PermissionResponse;

import java.util.List;

public interface PermissionService {
    PermissionResponse createPermission(PermissionRequest request);

    List<PermissionResponse> getPermissions();
}
