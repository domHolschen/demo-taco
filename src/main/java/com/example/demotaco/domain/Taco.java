package com.example.demotaco.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Taco {
    @NotBlank
    @Size(min=1, message = "Please enter a valid taco name (at least 1 character).")
    private String name;
    @Size(min=2, message = "You must have at least 2 ingredients to make a taco.")
    private List<String> ingredients;

    private Long id;
    private Date createdAt;
}
