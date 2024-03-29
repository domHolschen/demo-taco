package com.example.demotaco.domain;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
public class Order {
    @NotBlank(message = "Enter a non-blank name.")
    private String name;
    @NotBlank(message = "Enter a non-blank street address.")
    private String street;
    @NotBlank(message = "Enter a non-blank city.")
    private String city;
    @NotBlank(message = "Enter a non-blank state.")
    private String state;
    @Digits(integer = 5, fraction = 0, message = "Invalid zip code.")
    private String zip;
    @CreditCardNumber
    private String ccNumber;
    @Pattern(regexp = "(^0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    private Long id;
    private Date createdAt;

    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}
