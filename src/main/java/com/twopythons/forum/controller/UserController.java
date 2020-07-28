package com.twopythons.forum.controller;

import com.twopythons.forum.controller.mapper.UserMapper;
import com.twopythons.forum.model.dto.UserDto;
import com.twopythons.forum.model.entity.User;
import com.twopythons.forum.model.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController extends CommonController<UserMapper, UserServiceImpl, UserDto, User> {

    @Autowired
    public UserController(UserServiceImpl service, UserMapper mapper) {
        super(service, mapper);
    }

}
