package org.example.taskify.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.taskify.dto.ApiResponse;
import org.example.taskify.dto.request.PermissionRequest;
import org.example.taskify.dto.response.PermissionResponse;
import org.example.taskify.service.PermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ApiResponse<PermissionResponse> createPermission(PermissionRequest request) {
        return ApiResponse.success(201, permissionService.createPermission(request));
    }

    @GetMapping
    public ApiResponse<List<PermissionResponse>> getPermissions() {
        return ApiResponse.success(200, permissionService.getPermissions());
    }
}
