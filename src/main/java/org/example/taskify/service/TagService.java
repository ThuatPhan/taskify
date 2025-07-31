package org.example.taskify.service;

import org.example.taskify.dto.request.TagCreationRequest;
import org.example.taskify.dto.request.TagUpdateRequest;
import org.example.taskify.dto.response.PageResponse;
import org.example.taskify.dto.response.TagResponse;

public interface TagService {
    TagResponse createTag(TagCreationRequest request);

    PageResponse<TagResponse> getTags(int page, int size);

    TagResponse updateTag(String tagId, TagUpdateRequest request);

    void deleteTag(String tagId);
}
