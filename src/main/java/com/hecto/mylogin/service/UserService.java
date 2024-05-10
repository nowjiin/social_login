package com.hecto.mylogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hecto.mylogin.repository.UserRepository;

// 비지니스 로직 처리
// 필요한 데이터를 가져오는 곳
@Service
public class UserService {

    @Autowired UserRepository userRepository;

    public OAuth2Token getAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        // HttpHeader 객체를 생성
        // 헤더에 들어가는 정보
        // POST /oauth/token HTTP/1.1
        // Host : kauth.kakao.com
        // Content-type application/x-www-form-urlencoded
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        // Http body에 담을 요소 공식 문서 참조
        // required된 부분 넣기
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "{클라이언트 앱 키}");
        params.add("redirect_uri", "{리다이렉트 uri}");
        params.add("code", code);
        // params.add("client_secret", "{시크릿 키}"); // 생략 가능! 시크릿 키 네이버는 필 수

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        ResponseEntity<String> accessTokenResponse =
                restTemplate.exchange(
                        "https://kauth.kakao.com/oauth/token",
                        HttpMethod.POST,
                        kakaoTokenRequest,
                        String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        OAuth2Token oauth2Token = null;
        try {
            oauth2Token = objectMapper.readValue(accessTokenResponse.getBody(), OAuth2Token.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return oauth2Token; // (8)
    }
}
