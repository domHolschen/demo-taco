package com.example.demotaco.web;

import com.example.demotaco.data.OrderRepository;
import com.example.demotaco.data.UserRepository;
import com.example.demotaco.domain.Order;
import com.example.demotaco.domain.User;
import com.example.demotaco.security.MyUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {
    private OrderRepository orderRepository;
    private UserRepository userRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors error, SessionStatus sessionStatus) {
        if (error.hasErrors()) {
            return "orderForm";
        }
        Authentication fake = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails myUserDetails = (MyUserDetails) fake.getPrincipal();
        User user = userRepository.findById(myUserDetails.getId()).orElse(null);
        order.setUser(user);
        orderRepository.save(order);
        sessionStatus.setComplete();
        log.info("Order received: " + order);
        return "redirect:/";
    }
}
