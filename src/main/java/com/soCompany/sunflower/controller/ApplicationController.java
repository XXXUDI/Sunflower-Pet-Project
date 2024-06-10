package com.soCompany.sunflower.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    @GetMapping("/home")
    public String home() {
        return "main/index";
    }

    @GetMapping("/account")
    public String account() {
        return "overview/account/index";
    }

    @GetMapping("/communities")
    public String communities() {
        return "/communities/main";
    }


}
