package com.tienda.security.auth.ajax;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tienda.security.config.JwtSettings;
import com.tienda.security.model.UserContext;
import com.tienda.security.model.token.AccessJwtToken;
import com.tienda.security.model.token.JwtToken;
import com.tienda.security.model.token.JwtTokenFactory;

/**
 * AjaxAwareAuthenticationSuccessHandler
 * 
 * 
 *
 *         Dic 29 2016
 */
@Component
public class AjaxAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper mapper;
    private final JwtTokenFactory tokenFactory;
    
    @Autowired
    public AjaxAwareAuthenticationSuccessHandler(final ObjectMapper mapper, final JwtTokenFactory tokenFactory, JwtSettings settings) {
        this.mapper = mapper;
        this.tokenFactory = tokenFactory;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        UserContext userContext = (UserContext) authentication.getPrincipal();
        
        AccessJwtToken accessToken = tokenFactory.createAccessJwtToken(userContext);
        JwtToken refreshToken = tokenFactory.createRefreshToken(userContext);
      
        Map<String, String> tokenMap = new HashMap<String, String>();
        tokenMap.put("token", accessToken.getToken());
        tokenMap.put("refreshToken", refreshToken.getToken());
        tokenMap.put("expire", String.valueOf(accessToken.getClaims().getExpiration().getTime()));

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getWriter(), tokenMap);
        
        

        clearAuthenticationAttributes(request);
    }

    /**
     * Removes temporary authentication-related data which may have been stored
     * in the session during the authentication process..
     * 
     */
    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
