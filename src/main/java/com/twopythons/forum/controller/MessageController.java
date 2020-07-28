package com.twopythons.forum.controller;

import com.twopythons.forum.controller.mapper.MessageMapper;
import com.twopythons.forum.model.dto.MessageDto;
import com.twopythons.forum.model.entity.Message;
import com.twopythons.forum.model.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("messages")
public class MessageController extends CommonController<MessageMapper, MessageServiceImpl, MessageDto, Message> {

    @Autowired
    public MessageController(MessageServiceImpl service, MessageMapper mapper) {
        super(service, mapper);
    }

}
