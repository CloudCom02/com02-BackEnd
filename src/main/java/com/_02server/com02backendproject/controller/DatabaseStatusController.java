package com._02server.com02backendproject.controller;
import com._02server.com02backendproject.entities.User;
import com._02server.com02backendproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DatabaseStatusController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello");
        return "hello";
    }
    @GetMapping("/show-tables")
    public String showUsers(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "show-tables";
    }
}