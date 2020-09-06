package com.example.forumdisigin.data.model;

import com.example.forumdisigin.data.model.dto.AbstractDto;
import com.example.forumdisigin.data.model.dto.TagDto;
import com.example.forumdisigin.data.model.dto.UserDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class HttpManager {

    public <T extends AbstractDto> T[] getAll(Class<T[]> type) {

        String url = "http://localhost:8080/" + nameFromType(type) + "/all";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        String result = restTemplate.getForObject(url, String.class);
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(result, type);

    }

    private <T extends AbstractDto> String nameFromType(Class<T[]> type) {

        String raw = type.getName();
        int lastPoint = raw.lastIndexOf('.');
        int startDto = raw.indexOf("Dto");
        return raw.substring(lastPoint + 1, startDto).toLowerCase().concat("s");

    }


}
