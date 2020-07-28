package com.twopythons.forum.controller;

import com.twopythons.forum.controller.mapper.ThemeMapper;
import com.twopythons.forum.model.dto.ThemeDto;
import com.twopythons.forum.model.entity.Theme;
import com.twopythons.forum.model.service.impl.ThemeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("themes")
public class ThemeController extends CommonController<ThemeMapper, ThemeServiceImpl, ThemeDto, Theme> {

    @Autowired
    public ThemeController(ThemeServiceImpl service, ThemeMapper mapper) {
        super(service, mapper);
    }

}
