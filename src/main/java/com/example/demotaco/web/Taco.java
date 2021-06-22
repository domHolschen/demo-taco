package com.example.demotaco.web;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Taco {
    @NotNull
    @Size(min=1, message = "Please enter a valid taco name (at least 1 character).")
    private String name;
    @Size(min=2, message = "You must have at least 2 ingredients to make a taco.")
    private List<String> Ingredients;
}
