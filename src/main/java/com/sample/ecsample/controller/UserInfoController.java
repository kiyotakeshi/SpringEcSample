package com.sample.ecsample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserInfoController {
    @GetMapping("/user_info")
    public String getUserInfo(Model model) {
        return "user_info";
    }

    @PostMapping("/user_info")
    public String postUserInfo(Model model) {
        return "user_info";
    }
}
