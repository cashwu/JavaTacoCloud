package com.cashwu.javatacocloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author cash.wu
 * @since 2024/05/13
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {

        return "home";
    }
}
