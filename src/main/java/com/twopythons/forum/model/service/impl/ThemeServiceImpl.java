package com.twopythons.forum.model.service.impl;

import com.twopythons.forum.model.entity.Tag;
import com.twopythons.forum.model.entity.Theme;
import com.twopythons.forum.model.entity.enums.ThemeState;
import com.twopythons.forum.model.repository.ThemeRepository;
import com.twopythons.forum.model.service.Voteable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ThemeServiceImpl extends ServiceCommonImpl<Theme, ThemeRepository> implements Voteable {

    private final TagServiceImpl tagService;
    private final UserServiceImpl userService;

    @Autowired
    public ThemeServiceImpl(ThemeRepository repository, TagServiceImpl tagService, UserServiceImpl userService) {

        super(repository);
        this.tagService = tagService;
        this.userService = userService;

    }

    @Transactional
    public void changeThemeStateById(Long id, ThemeState themeState) {

        getById(id).ifPresent(theme -> theme.setState(themeState));

    }

    public void addTag(Theme theme, String tagTitle) {

        Tag tag = tagService.getTagByName(tagTitle);
        theme.getTags().add(tag);
        repository.save(theme);

    }

    public void removeTag(Theme theme, String tagTitle) {

        theme.getTags().removeIf(tag -> tag.getName().equals(tagTitle));
        repository.save(theme);

    }

    public List<Theme> getAllThemesByUserId(Long id) {
        return repository.getAllByUserId(id);
    }

    @Override
    public void upvote(Long themeId, Long userId) {

        getById(themeId).ifPresent(theme -> {

            if(theme.getAuthor().getId().equals(userId)) {
                return;
            }

            if(theme.getUpvotes().stream().anyMatch(user -> user.getId().equals(userId))) {

                theme.getUpvotes().removeIf(user -> user.getId().equals(userId));
                repository.save(theme);
                return;
            }

            if(theme.getDownvotes().stream().anyMatch(user -> user.getId().equals(userId))) {

                theme.getDownvotes().removeIf(user -> user.getId().equals(userId));

            }

            userService.getById(userId).ifPresent(user -> theme.getUpvotes().add(user));

            repository.save(theme);

        });
    }

    @Override
    public void downvote(Long themeId, Long userId) {

        getById(themeId).ifPresent(theme -> {

            if(theme.getAuthor().getId().equals(userId)) {
                return;
            }

            if(theme.getDownvotes().stream().anyMatch(user -> user.getId().equals(userId))) {

                theme.getDownvotes().removeIf(user -> user.getId().equals(userId));
                repository.save(theme);
                return;
            }

            if(theme.getUpvotes().stream().anyMatch(user -> user.getId().equals(userId))) {

                theme.getUpvotes().removeIf(user -> user.getId().equals(userId));

            }

            userService.getById(userId).ifPresent(user -> theme.getDownvotes().add(user));

            repository.save(theme);

        });

    }
}
