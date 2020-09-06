package com.example.forumdisigin.data.model;

import com.example.forumdisigin.data.model.dto.MessageDto;
import com.example.forumdisigin.data.model.dto.TagDto;
import com.example.forumdisigin.data.model.dto.ThemeDto;
import com.example.forumdisigin.data.model.dto.UserDto;

import org.junit.Test;

import static org.junit.Assert.*;

public class HttpManagerTest {

    @Test
    public void tryMethod() {

        HttpManager manager = new HttpManager();

        TagDto tagDto = manager.getAll(TagDto[].class)[1];

        System.out.println("id " + tagDto.getId() + " name " + tagDto.getName());


    }
}