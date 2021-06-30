package com.example.demotaco.web;

import com.example.demotaco.data.UserRepository;
import com.example.demotaco.security.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class HomeController {
    private UserRepository userRepository;
    @Autowired
    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ModelAttribute(name="newUser")
    private Registration registerUser() {
        return new Registration();
    }

    @PostMapping("/register")
    public String registerForm(@ModelAttribute(name="newUser")Registration registration) {
        //todo: validation
        userRepository.save(registration.convertUser());
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
