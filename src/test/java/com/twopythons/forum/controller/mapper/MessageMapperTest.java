package com.twopythons.forum.controller.mapper;

import com.twopythons.forum.model.dto.MessageDto;
import com.twopythons.forum.model.entity.Message;
import com.twopythons.forum.model.service.impl.MessageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.GregorianCalendar;

@RunWith(SpringRunner.class)
@SpringBootTest
class MessageMapperTest {

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    MessageServiceImpl service;

    @Test
    public void dtoToEntity() {

        MessageDto dto = new MessageDto();
        dto.setId(2L);
        dto.setDate(new GregorianCalendar(2001, Calendar.MARCH,2));
        dto.setAuthorId(2L);
        dto.setThemeId(1L);

        Message entity = messageMapper.dtoToEntity(dto);

        System.out.println(entity.getAuthor().getId() + " " + entity.getTheme().getId());

    }

}