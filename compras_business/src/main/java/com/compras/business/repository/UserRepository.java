package com.compras.business.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.compras.domain.model.Usuario;


/**
 * UserRepository
 */
public interface UserRepository extends JpaRepository<Usuario, Long> {
    @Query("select u from Usuario u where u.email=:email")
    public Optional<Usuario> findByEmail(@Param("email") String email);
    
    @Query("select u from Usuario u where u.email=:email")
    public Usuario findUserByEmail(@Param("email") String email);
    
}
