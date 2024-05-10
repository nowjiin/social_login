package com.hecto.mylogin.controller;

import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hecto.mylogin.service.UserService;

// 사용가 관련 기능 관리 API
@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    // 프론트에서 "code"인가 코드 받아오는 url api
    @GetMapping("/oauth/token")
    public OAuth2Token getLogin(@RequestParam("code") String code) {
        OAuth2Token oAuth2Token = userService.getAccessToken(code);

        System.out.println(oAuth2Token);
        return oAuth2Token;
    }
}
