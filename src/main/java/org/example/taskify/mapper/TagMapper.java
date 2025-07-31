package org.example.taskify.mapper;

import java.util.List;

import org.example.taskify.dto.request.TagCreationRequest;
import org.example.taskify.dto.request.TagUpdateRequest;
import org.example.taskify.dto.response.TagResponse;
import org.example.taskify.entity.Tag;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TagMapper {
    Tag toEntity(TagCreationRequest request);

    TagResponse toResponse(Tag tag);

    List<TagResponse> toResponseList(List<Tag> tags);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Tag tag, TagUpdateRequest request);
}
