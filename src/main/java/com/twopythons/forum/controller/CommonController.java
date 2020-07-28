package com.twopythons.forum.controller;

import com.twopythons.forum.controller.mapper.CommonMapper;
import com.twopythons.forum.model.dto.AbstractDto;
import com.twopythons.forum.model.entity.AbstractEntity;
import com.twopythons.forum.model.service.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public abstract class CommonController <M extends CommonMapper<E, D>, S extends Service<E>, D extends AbstractDto, E extends AbstractEntity> {

    protected final M mapper;
    protected final S service;

    public CommonController(S service, M mapper) {
        this.service = service;
        this.mapper = mapper;

    }

    @GetMapping(value = {"/all"})
    public List<D> getList() {
        return mapper.entitiesToDtos(service.getAll());
    }

    @GetMapping(value = {"/{id}"})
    public D findById(@PathVariable("id") Long id) {
        return mapper.entityToDto(service.getById(id).orElse(null));
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void save( @Valid @RequestBody D dto) {
        service.create(mapper.dtoToEntity(dto));
    }

}
