package com.tienda.security.endpoint;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.tienda.security.auth.exceptions.InvalidJwtToken;
import com.tienda.security.auth.extractor.TokenExtractor;
import com.tienda.security.auth.jwt.verifier.TokenVerifier;
import com.tienda.security.config.JwtSettings;
import com.tienda.security.config.WebSecurityConfig;
import com.tienda.security.model.UserContext;
import com.tienda.security.model.token.JwtTokenFactory;
import com.tienda.security.model.token.RawAccessJwtToken;
import com.tienda.security.model.token.RefreshToken;


/**
 * RefreshTokenEndpoint
 * 
 * 
 *
 * Aug 17, 2016
 */
public abstract class RefreshTokenEndpoint {
    @Autowired protected JwtTokenFactory tokenFactory;
    @Autowired private JwtSettings jwtSettings;
    @Autowired private TokenVerifier tokenVerifier;
    @Autowired @Qualifier("jwtHeaderTokenExtractor") private TokenExtractor tokenExtractor;
    
    
    public Object refreshToken(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM));
        
        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(
        								rawToken, 
        								Base64.getEncoder().encodeToString(jwtSettings.getTokenSigningKey().getBytes()))
        							.orElseThrow(() -> new InvalidJwtToken());

        String jti = refreshToken.getJti();
        if (!tokenVerifier.verify(jti)) {
            throw new InvalidJwtToken();
        }

        String subject = refreshToken.getSubject();
        UserContext userContext = getUserWithPermissions(subject);

        return tokenFactory.createAccessJwtToken(userContext);
    }

	protected abstract  UserContext getUserWithPermissions(String subject) throws Throwable;
}
