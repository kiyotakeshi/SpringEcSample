package com.sample.ecsample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NewLoginController {

    @GetMapping("/new_login")
    public String getNewLogin(Model model) {
        return "new_login";
    }

    @PostMapping("/new_login")
    public String postNewLogin(Model model) {
        return "new_login";
    }
}
