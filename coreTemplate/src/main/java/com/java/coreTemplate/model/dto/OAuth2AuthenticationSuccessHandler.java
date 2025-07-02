package com.java.coreTemplate.model.dto;

import com.java.coreTemplate.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private HttpCookieOAuth2AuthorizationRequestRepository authRequestRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String token = jwtTokenProvider.generateToken(authentication);

        // You can redirect with token or set it in cookies
        String targetUrl = determineTargetUrl(request, response, authentication);
        targetUrl += "?token=" + token;

        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        authRequestRepository.removeAuthorizationRequestCookies(request, response);
    }
}