package org.example.taskify.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.example.taskify.dto.request.TagCreationRequest;
import org.example.taskify.dto.request.TagUpdateRequest;
import org.example.taskify.dto.response.ApiResponse;
import org.example.taskify.dto.response.PageResponse;
import org.example.taskify.dto.response.TagResponse;
import org.example.taskify.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Tag(name = "Tag API")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TagController {
    TagService tagService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<TagResponse> createTag(@RequestBody @Valid TagCreationRequest request) {
        return ApiResponse.success(201, tagService.createTag(request));
    }

    @GetMapping
    public ApiResponse<PageResponse<TagResponse>> getTags(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return ApiResponse.success(200, tagService.getTags(page, size));
    }

    @PutMapping("/{tagId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<TagResponse> updateTag(
            @PathVariable String tagId, @RequestBody @Valid TagUpdateRequest request) {
        return ApiResponse.success(200, tagService.updateTag(tagId, request));
    }

    @DeleteMapping("/{tagId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable String tagId) {
        tagService.deleteTag(tagId);
    }
}
