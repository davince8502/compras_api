package com.tienda.security.model.token;

import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.tienda.security.config.JwtSettings;
import com.tienda.security.model.Scopes;
import com.tienda.security.model.UserContext;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Factory class that should be always used to create {@link JwtToken}.
 * 
 * 
 *
 * May 31, 2016
 */
@Component
public class JwtTokenFactory {
    private final JwtSettings settings;

    @Autowired
    public JwtTokenFactory(JwtSettings settings) {
        this.settings = settings;
    }

    /**
     * Factory method for issuing new JWT Tokens.
     * 
     * @param username
     * @param roles
     * @return
     */
    public AccessJwtToken createAccessJwtToken(UserContext userContext) {
        if (StringUtils.isBlank(userContext.getUsername())) 
            throw new IllegalArgumentException("Cannot create JWT Token without username");

        if (userContext.getAuthorities() == null || userContext.getAuthorities().isEmpty()) 
            throw new IllegalArgumentException("User doesn't have any privileges");

        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
        
        if(!CollectionUtils.isEmpty(userContext.getAuthorities())){
        	  claims.put("scopes", userContext.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));
        }
        
      
        if(userContext.getDataUser() != null){
        	claims.put("dataUser", userContext.getDataUser());
        }        

        DateTime currentTime = new DateTime();
        
        
        String token = Jwts.builder()
          .setClaims(claims)
          .setIssuer(settings.getTokenIssuer())
          .setIssuedAt(currentTime.toDate())
          .setExpiration(currentTime.plusMinutes(settings.getTokenExpirationTime()).toDate())
          .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(settings.getTokenSigningKey().getBytes()))
        .compact();

        return new AccessJwtToken(token, claims, String.valueOf(claims.getExpiration().getTime()));
    }

    public JwtToken createRefreshToken(UserContext userContext) {
        if (StringUtils.isBlank(userContext.getUsername())) {
            throw new IllegalArgumentException("Cannot create JWT Token without username");
        }

        DateTime currentTime = new DateTime();

        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
        claims.put("scopes", Arrays.asList(Scopes.REFRESH_TOKEN.authority()));
        
        String token = Jwts.builder()
          .setClaims(claims)
          .setIssuer(settings.getTokenIssuer())
          .setId(UUID.randomUUID().toString())
          .setIssuedAt(currentTime.toDate())
          .setExpiration(currentTime.plusMinutes(settings.getRefreshTokenExpTime()).toDate())
          .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(settings.getTokenSigningKey().getBytes()))
        .compact();

        return new AccessJwtToken(token, claims, String.valueOf(claims.getExpiration().getTime()));
    }
}
