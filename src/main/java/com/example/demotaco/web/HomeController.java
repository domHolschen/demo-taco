package com.example.demotaco.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/home")
    public String homePost() {
        // insert data somewhere special
        return "success";
    }

    @PutMapping("/home")
    public String homePut() {
        // find some matching element
        // update that element
        // return a message
        return "we did";
    }
}
