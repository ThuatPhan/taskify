package org.example.taskify.service;

import org.example.taskify.dto.request.TagCreationRequest;
import org.example.taskify.dto.request.TagUpdateRequest;
import org.example.taskify.dto.response.PageResponse;
import org.example.taskify.dto.response.TagResponse;
import org.example.taskify.entity.Tag;
import org.example.taskify.entity.User;
import org.example.taskify.exception.AppException;
import org.example.taskify.exception.ErrorCode;
import org.example.taskify.mapper.TagMapper;
import org.example.taskify.repository.TagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TagServiceImpl implements TagService {
    UserService userService;
    TagRepository tagRepository;
    TagMapper tagMapper;

    @PreAuthorize("isAuthenticated()")
    @Override
    public TagResponse createTag(TagCreationRequest request) {
        boolean isExistsTag = tagRepository.existsByName(request.getName());
        if (isExistsTag) {
            throw new AppException(ErrorCode.TAG_ALREADY_EXISTS);
        }
        Tag newTag = tagMapper.toEntity(request);

        User user = userService.getCurrentUser();
        newTag.setCreatedBy(user);

        Tag savedTag = tagRepository.save(newTag);

        return tagMapper.toResponse(savedTag);
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public PageResponse<TagResponse> getTags(int page, int size) {
        Page<Tag> tagsPage = tagRepository.findAll(PageRequest.of(page, size));
        return PageResponse.of(tagsPage.hasNext(), tagMapper.toResponseList(tagsPage.getContent()));
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public TagResponse updateTag(String tagId, TagUpdateRequest request) {
        Tag tagToUpdate = tagRepository.findById(tagId).orElseThrow(() -> new AppException(ErrorCode.TAG_NOT_FOUND));
        tagMapper.partialUpdate(tagToUpdate, request);

        if (tagRepository.existsByName(tagToUpdate.getName())
                && !tagToUpdate.getId().equals(tagId)) {
            throw new AppException(ErrorCode.TAG_ALREADY_EXISTS);
        }

        return tagMapper.toResponse(tagRepository.save(tagToUpdate));
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public void deleteTag(String tagId) {
        Tag tagToDelete = tagRepository.findById(tagId).orElseThrow(() -> new AppException(ErrorCode.TAG_NOT_FOUND));
        tagRepository.delete(tagToDelete);
    }
}
