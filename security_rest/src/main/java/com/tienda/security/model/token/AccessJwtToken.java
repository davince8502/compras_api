package com.tienda.security.model.token;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.jsonwebtoken.Claims;

/**
 * Raw representation of JWT Token.
 * 
 * 
 *
 *         May 31, 2016
 */
public final class AccessJwtToken implements JwtToken {
    
	private final String rawToken;
	private final String expireTime;
    @JsonIgnore private Claims claims;

    protected AccessJwtToken(final String token, Claims claims, final String expireTime) {
        this.rawToken = token;
        this.claims = claims;
        this.expireTime = expireTime;
    }

    public String getToken() {
        return this.rawToken;
    }
    
    public Claims getClaims() {
        return claims;
    }

	public String getExpireTime() {
		return expireTime;
	}
}
