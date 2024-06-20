package com.hyundairoad.hyundairoad.global.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CustomOAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", generateToken(authentication));
        writer.write(new ObjectMapper().writeValueAsString(tokenMap));
        writer.flush();
    }

    private String generateToken(Authentication authentication) {
        // JWT 토큰 생성 로직 추가
        return "generated-jwt-token";
    }
}
