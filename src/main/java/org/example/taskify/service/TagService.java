package org.example.taskify.service;

import org.example.taskify.dto.request.TagCreationRequest;
import org.example.taskify.dto.request.TagUpdateRequest;
import org.example.taskify.dto.response.PageResponse;
import org.example.taskify.dto.response.TagResponse;
import org.springframework.security.access.prepost.PreAuthorize;

public interface TagService {
    @PreAuthorize("isAuthenticated()")
    TagResponse createTag(TagCreationRequest request);

    @PreAuthorize("isAuthenticated()")
    PageResponse<TagResponse> getTags(int page, int size);

    @PreAuthorize("isAuthenticated()")
    TagResponse updateTag(String tagId, TagUpdateRequest request);

    @PreAuthorize("isAuthenticated()")
    void deleteTag(String tagId);
}
