package com.twopythons.forum.model.service.impl;

import com.twopythons.forum.ForumApplication;
import com.twopythons.forum.model.entity.Message;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ForumApplication.class)
class MessageServiceImplTest {

    @Autowired
    private MessageServiceImpl messageService;

    @Test
    public void reply() {

        Message question = messageService.getById(1L).orElse(new Message());
        Message answer = messageService.getById(2L).orElse(new Message());
        messageService.reply(question, answer);
        Assert.assertEquals(1L, (long) messageService.getById(2L).orElse(new Message()).getRepliedTo().getId());

    }

    @Test
    @Transactional
    public void votesTest() {

        messageService.getById(1L).ifPresent(message -> {

            message.getUpvotes().clear();
            message.getDownvotes().clear();

            //upvoting by random user increases rating
            messageService.upvote(1L, 4L);
            Assert.assertEquals(1, messageRating(message));

            //second upvote cancel first upvote
            messageService.upvote(1L, 4L);
            Assert.assertEquals(0, messageRating(message));

            //author of message can't upvote or downvote his own message
            messageService.upvote(1L, 1L);
            Assert.assertEquals(0, messageRating(message));

            //downvote test
            messageService.downvote(1L, 6L);
            messageService.downvote(1L, 7L);
            Assert.assertEquals(-2, messageRating(message));

            //double downvote test
            messageService.downvote(1L, 6L);
            Assert.assertEquals(-1, messageRating(message));

        });

    }

    private int messageRating(Message message) {
        return message.getUpvotes().size() - message.getDownvotes().size();
    }

}