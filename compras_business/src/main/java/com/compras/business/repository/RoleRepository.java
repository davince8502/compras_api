package com.compras.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compras.domain.model.Role;


/**
 * UserRepository
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    
}
