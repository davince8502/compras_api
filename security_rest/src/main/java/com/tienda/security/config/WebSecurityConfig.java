package com.tienda.security.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tienda.security.auth.ajax.AjaxAuthenticationProvider;
import com.tienda.security.auth.ajax.AjaxLoginProcessingFilter;
import com.tienda.security.auth.extractor.TokenExtractor;
import com.tienda.security.auth.jwt.JwtAuthenticationProvider;
import com.tienda.security.auth.jwt.JwtTokenAuthenticationProcessingFilter;
import com.tienda.security.auth.jwt.SkipPathRequestMatcher;
import com.tienda.security.endpoint.RestAuthenticationEntryPoint;


/**
 * WebSecurityConfig
 * 
 * 
 *
 * Aug 3, 2016
 */
@Configuration
@EnableWebSecurity
@Order(100)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";
    public static final String FORM_BASED_LOGIN_ENTRY_POINT = "/auth/login";
    public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/**";
    public static final String TOKEN_REFRESH_ENTRY_POINT = "/auth/token";
    
    @Autowired private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired private AuthenticationSuccessHandler successHandler;
    @Autowired private AuthenticationFailureHandler failureHandler;
    @Autowired private AjaxAuthenticationProvider ajaxAuthenticationProvider;
    @Autowired private JwtAuthenticationProvider jwtAuthenticationProvider;
    
    @Autowired private TokenExtractor tokenExtractor;    
    @Autowired private AuthenticationManager authenticationManager;    
    @Autowired private ObjectMapper objectMapper;    
    @Autowired private WebSettings webSettings;
        
    protected AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter() throws Exception {
        AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(FORM_BASED_LOGIN_ENTRY_POINT, successHandler, failureHandler, objectMapper);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }
    
    protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter() throws Exception {
        List<String> pathsToSkip = new ArrayList<>(Arrays.asList(TOKEN_REFRESH_ENTRY_POINT, FORM_BASED_LOGIN_ENTRY_POINT));
        
        if(StringUtils.isNotBlank(webSettings.getPathWihtoutAuth())){
        	pathsToSkip.add(webSettings.getPathWihtoutAuth());
        }
        
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, ((StringUtils.isBlank(webSettings.getTokenBasedAuthEntryPoint()))? 
        		TOKEN_BASED_AUTH_ENTRY_POINT :webSettings.getTokenBasedAuthEntryPoint()));
        JwtTokenAuthenticationProcessingFilter filter 
            = new JwtTokenAuthenticationProcessingFilter(failureHandler, tokenExtractor, matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(ajaxAuthenticationProvider);
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
  
    	
        http
        .csrf().disable() // We don't need CSRF for JWT based authentication
        .exceptionHandling()
        .authenticationEntryPoint(this.authenticationEntryPoint)
        
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        .and()
            .authorizeRequests()
                .antMatchers(FORM_BASED_LOGIN_ENTRY_POINT).permitAll() // Login end-point
                .antMatchers(TOKEN_REFRESH_ENTRY_POINT).permitAll() // Token refresh end-point
                .antMatchers((StringUtils.isBlank(webSettings.getPathWihtoutAuth()))?
                		StringUtils.EMPTY : webSettings.getPathWihtoutAuth()).permitAll() 
        .and()
            .authorizeRequests()
                .antMatchers((StringUtils.isBlank(webSettings.getTokenBasedAuthEntryPoint()))? 
                		TOKEN_BASED_AUTH_ENTRY_POINT :webSettings.getTokenBasedAuthEntryPoint()).authenticated() // Protected API End-points
        .and()
//            .addFilterBefore(buildAjaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }


	public AjaxAuthenticationProvider getAjaxAuthenticationProvider() {
		return ajaxAuthenticationProvider;
	}

	public RestAuthenticationEntryPoint getAuthenticationEntryPoint() {
		return authenticationEntryPoint;
	}
	

	public HttpSecurity getHttpSecurity() throws Exception{
		return super.getHttp();
	}

}
