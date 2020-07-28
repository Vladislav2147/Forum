package com.twopythons.forum.controller.mapper;

import com.twopythons.forum.model.dto.MessageDto;
import com.twopythons.forum.model.entity.Message;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MessageMapper extends CommonMapper<Message, MessageDto> {

    @Override
    @Mapping(source = "theme", target = "themeId", qualifiedByName = "getIdOfEntity")
    @Mapping(source = "author", target = "authorId", qualifiedByName = "getIdOfEntity")
    @Mapping(source = "repliedTo", target = "repliedToMessageId", qualifiedByName = "getIdOfEntity")
    @Mapping(source = "repliedBy", target = "repliedByMessagesId", qualifiedByName = "getIdOfEntity")
    MessageDto entityToDto(Message entity);

    @Override
    @Mapping(source = "themeId", target = "theme", qualifiedByName = "getThemeById")
    @Mapping(source = "authorId", target = "author", qualifiedByName = "getUserById")
    @Mapping(source = "repliedToMessageId", target = "repliedTo", qualifiedByName = "getMessageById")
    @Mapping(source = "repliedByMessagesId", target = "repliedBy", qualifiedByName = "getMessageById")
    Message dtoToEntity(MessageDto dto);

    @AfterMapping
    default void after(@MappingTarget MessageDto dto, Message message) {
        dto.setRating(message.getUpvotes().size() - message.getDownvotes().size());
    }

}
