package com.example.demotaco.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {
    @NotBlank
    @Size(min=1, message = "Please enter a valid taco name (at least 1 character).")
    private String name;
    @Size(min=2, message = "You must have at least 2 ingredients to make a taco.")
    @ManyToMany(targetEntity = Ingredient.class)
    private List<String> ingredients;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date createdAt;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}
