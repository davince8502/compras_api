package com.compras.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tienda.security.model.UserContext;

public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Long getCurrentAuditor() {       
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        
        if(!authentication.getPrincipal().equals("anonymousUser")){
        	Integer idUser = (Integer) ((UserContext) authentication.getPrincipal()).getDataUser();
        	 
            return Long.valueOf(idUser.intValue());
        }
        
        return null;
        
        
        
    }
}
