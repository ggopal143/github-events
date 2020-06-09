package com.example.github.events.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GitHubEventsController {

    @GetMapping("/")
    public String main(Model model) {
        return "home";
    }
}
