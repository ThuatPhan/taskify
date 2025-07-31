package org.example.taskify.service;

import java.util.List;

import org.example.taskify.dto.request.RoleRequest;
import org.example.taskify.dto.response.RoleResponse;

public interface RoleService {
    RoleResponse createRole(RoleRequest request);

    List<RoleResponse> getRoles();
}
