package hyundairoad.hyundairoad.global.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
        return "generated-jwt-token";
    }
}
