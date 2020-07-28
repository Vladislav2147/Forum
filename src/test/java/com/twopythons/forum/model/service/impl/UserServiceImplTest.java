package com.twopythons.forum.model.service.impl;

import com.twopythons.forum.ForumApplication;
import com.twopythons.forum.model.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ForumApplication.class)
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    public void update() {

        userService.getById(1L).ifPresent(user -> {
            String newPassword = "87654321";
            String newLogin = "whatisluve";
            user.setPassword(newPassword);
            user.setLogin(newLogin);
            userService.update(user);
            Assert.assertEquals(newPassword, userService.getById(1L).orElse(new User()).getPassword());
            Assert.assertEquals(newLogin, userService.getById(1L).orElse(new User()).getLogin());
        });

    }

    @Test
    public void banUnbanById() {

        boolean notBanned;

        userService.banById(1L);
        notBanned = userService.getById(1L).orElse(new User()).isAccountNonLocked();

        Assert.assertFalse(notBanned);

        userService.unbanById(1L);
        notBanned = userService.getById(1L).orElse(new User()).isAccountNonLocked();

        Assert.assertTrue(notBanned);

    }

    @Test
    public void changeRoles() {

        Long id = 1L;

        userService.addRole(1L, "ROLE_GUEST");
        userService.addRole(1L, "ROLE_USER");
        userService.addRole(1L, "ROLE_ADMIN");

        User user = userService.getById(id).orElse(new User());
        Assert.assertEquals(3, user.getRoles().size());

        userService.removeRole(1L, "ROLE_ADMIN");

        user = userService.getById(id).orElse(new User());
        Assert.assertEquals(2, user.getRoles().size());

    }

}