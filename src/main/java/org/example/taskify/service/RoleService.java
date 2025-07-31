package org.example.taskify.service;

import org.example.taskify.dto.request.RoleRequest;
import org.example.taskify.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse createRole(RoleRequest request);

    List<RoleResponse> getRoles();
}
