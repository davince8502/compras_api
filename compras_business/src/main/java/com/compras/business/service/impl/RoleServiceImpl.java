package com.compras.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compras.business.repository.RoleRepository;
import com.compras.business.service.RoleService;
import com.compras.domain.model.Role;

/**
 * RoleService implementation.
 */
@Service
public class RoleServiceImpl implements RoleService {
	
    private final RoleRepository roleRepository;
    
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

	@Override
	public Role findById(Long id) {		
		return this.roleRepository.findOne(id);
	}

    
}
