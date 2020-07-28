package com.twopythons.forum.model.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.Collection;

@Data
public class TagDto extends AbstractDto {

    @Pattern(regexp = "\\W(#[a-zA-Z]+\\b)(?!;)")
    private String name;
    private Collection<Long> themesId;

}
