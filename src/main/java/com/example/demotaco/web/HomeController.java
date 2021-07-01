package com.example.demotaco.web;

import com.example.demotaco.data.UserRepository;
import com.example.demotaco.domain.User;
import com.example.demotaco.security.Registration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@Slf4j
public class HomeController {
    private UserRepository userRepository;
    private final PasswordEncoder encoder;
    @Autowired
    public HomeController(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @ModelAttribute(name="newUser")
    private Registration registerUser() {
        return new Registration();
    }

    @PostMapping("/register")
    public String registerForm(@ModelAttribute(name="newUser")Registration registration) {
        //todo: validation
        User squirrel = registration.convertUser(encoder);
        userRepository.save(squirrel);
        log.info("Mr. Squirrel: "+squirrel);
        return "redirect:/login";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("newUser",registerUser());
        return "register";
    }
}
