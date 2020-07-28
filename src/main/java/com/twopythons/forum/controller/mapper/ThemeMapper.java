package com.twopythons.forum.controller.mapper;

import com.twopythons.forum.model.dto.ThemeDto;
import com.twopythons.forum.model.entity.Theme;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ThemeMapper extends CommonMapper<Theme, ThemeDto> {

    @Override
    @Mapping(source = "messages", target = "messagesId", qualifiedByName = "getIdOfEntity")
    @Mapping(source = "tags", target = "tagsId", qualifiedByName = "getIdOfEntity")
    @Mapping(source = "author", target = "authorId", qualifiedByName = "getIdOfEntity")
    ThemeDto entityToDto(Theme entity);

    @Override
    @Mapping(source = "messagesId", target = "messages", qualifiedByName = "getMessageById")
    @Mapping(source = "tagsId", target = "tags", qualifiedByName = "getTagById")
    @Mapping(source = "authorId", target = "author", qualifiedByName = "getUserById")
    Theme dtoToEntity(ThemeDto dto);

    @AfterMapping
    default void after(@MappingTarget ThemeDto dto, Theme theme) {
        dto.setRating(theme.getUpvotes().size() - theme.getDownvotes().size());
    }

}
