package com.twopythons.forum.model.service.impl;

import com.twopythons.forum.model.entity.Message;
import com.twopythons.forum.model.repository.MessageRepository;
import com.twopythons.forum.model.service.Voteable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl extends ServiceCommonImpl<Message, MessageRepository> implements Voteable {

    private final UserServiceImpl userService;

    @Autowired
    public MessageServiceImpl(MessageRepository repository, UserServiceImpl userService) {

        super(repository);
        this.userService = userService;

    }

    public void reply(Message question, Message answer) {

        question.getRepliedBy().add(answer);
        answer.setRepliedTo(question);
        repository.save(question);
        repository.save(answer);

    }

    @Override
    public void upvote(Long messageId, Long userId) {

        getById(messageId).ifPresent(message -> {

            if(message.getAuthor().getId().equals(userId)) {
                return;
            }

            if(message.getUpvotes().stream().anyMatch(user -> user.getId().equals(userId))) {

                message.getUpvotes().removeIf(user -> user.getId().equals(userId));
                repository.save(message);
                return;
            }

            if(message.getDownvotes().stream().anyMatch(user -> user.getId().equals(userId))) {

                message.getDownvotes().removeIf(user -> user.getId().equals(userId));

            }

            userService.getById(userId).ifPresent(user -> message.getUpvotes().add(user));

            repository.save(message);

        });

    }

    @Override
    public void downvote(Long messageId, Long userId) {

        getById(messageId).ifPresent(message -> {

            if(message.getAuthor().getId().equals(userId)) {
                return;
            }

            if(message.getDownvotes().stream().anyMatch(user -> user.getId().equals(userId))) {

                message.getDownvotes().removeIf(user -> user.getId().equals(userId));
                repository.save(message);
                return;
            }

            if(message.getUpvotes().stream().anyMatch(user -> user.getId().equals(userId))) {

                message.getUpvotes().removeIf(user -> user.getId().equals(userId));

            }

            userService.getById(userId).ifPresent(user -> message.getDownvotes().add(user));

            repository.save(message);

        });

    }

}
