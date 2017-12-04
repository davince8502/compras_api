package com.compras.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compras.domain.model.UsuarioRole;


/**
 * UserRepository
 * 
 */
public interface UserRoleRepository extends JpaRepository<UsuarioRole, Long> {
    
    
}
