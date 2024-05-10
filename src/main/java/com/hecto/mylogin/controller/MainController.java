package com.hecto.mylogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 메인 페이지 요청 처리
@Controller
public class MainController {
    @GetMapping("/")
    public String mainPage() {
        return "<h1>main</h1>";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "<h1>Login</h1>";
    }
}
