package com.compras.business.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.compras.business.repository.CompraRepository;
import com.compras.business.repository.UserRepository;
import com.compras.business.repository.UserRoleRepository;
import com.compras.business.service.UserService;
import com.compras.commons.contants.Constantes;
import com.compras.commons.emuns.ErrorCodeEnum;
import com.compras.commons.exception.ValidationException;
import com.compras.domain.model.Compra;
import com.compras.domain.model.Usuario;
import com.compras.domain.model.UsuarioRole;
import com.tienda.security.model.UserContext;


/**
 * implementación del servicio {@link UserService} para el menejo de lógica de Usuarios. 
 * 
 * @author 	MHernandez <br>
 * @date 01 de Diciembre de 2017
 * @version 1.0
 */

@Service

public class UserServiceImpl implements UserService {
	
	@Autowired
    private  UserRepository userRepository;
    
	@Autowired
    private UserRoleRepository userRoleRepository;
	
	@Autowired
    private CompraRepository compraRepository;
    
    @Autowired
    private BCryptPasswordEncoder encoder;
   
    
    public UserRepository getUserRepository() {
        return userRepository;
    }

	@Override
	public UserContext validateUserContext(String email, String password) throws ValidationException {
  	 
		Usuario user = this.userRepository.findByEmail(email)
				.orElseThrow(() -> new ValidationException(ErrorCodeEnum.USER_NO_FOUND));

		if (!encoder.matches(password, user.getPassword())) {
			throw new ValidationException(ErrorCodeEnum.BAD_PASSWORD);
		}

		if (user.getUsuarioRoles().isEmpty())
			throw new ValidationException(ErrorCodeEnum.SIN_ROLES);

		List<UsuarioRole> userRoles = user.getUsuarioRoles();

		List<GrantedAuthority> authorities = userRoles.stream()
				.map(authority -> new SimpleGrantedAuthority(authority.getRole().getNombre()))
				.collect(Collectors.toList());

		UserContext userContext = UserContext.create(user.getUsername(), authorities, user.getId());

		return userContext;
	}
    

	@Override
	public Usuario getUserByEmail(String email) throws Exception {
		
		Usuario user = this.userRepository.findUserByEmail(email);
		
		if(user != null){
			user.setPassword(null);			
			return user;
		}else{
			new ValidationException(ErrorCodeEnum.USER_NO_FOUND);
		}
		
		return null;	
	}
	
	

	@Override
	@Transactional
	public void crearUsuario(Usuario usuario) throws Exception {
		
		Usuario OldUser = getUserByEmail(usuario.getEmail());
		
		if(OldUser != null){
			new ValidationException(ErrorCodeEnum.EMAIL_REPETIDO, usuario.getEmail());
		}
		
		OldUser = this.userRepository.findUserByNumIdentificacion(usuario.getNumeroDocumento());
		
		if(OldUser != null){
			new ValidationException(ErrorCodeEnum.NI_REPETIDO, usuario.getEmail());
		}
		
		this.userRepository.save(usuario);
		
		if(CollectionUtils.isNotEmpty(usuario.getUsuarioRoles())){		
			userRoleRepository.save(usuario.getUsuarioRoles());
		}
		
	}

	

	@Override
	@Transactional
	public void updateUsuario(Usuario usuario) throws Exception {
		
		Usuario oldUsuario =  this.userRepository.findOne(usuario.getId());
		
		if(oldUsuario == null){
			throw new ValidationException(ErrorCodeEnum.USER_NO_FOUND, usuario.getId());
		}		
		
		if(CollectionUtils.isNotEmpty(oldUsuario.getUsuarioRoles())){		
			userRoleRepository.delete(oldUsuario.getUsuarioRoles());
		}
		
		if(CollectionUtils.isNotEmpty(usuario.getUsuarioRoles())){		
			userRoleRepository.save(usuario.getUsuarioRoles());
		}	
		
		this.userRepository.save(usuario);	
		
		
		
		
	}
	
	@Override
	@Transactional
	public void deleteUsuario(Long id) throws Exception {


		Usuario user = this.userRepository.findOne(id);
		
		if(user == null){
			throw new ValidationException(ErrorCodeEnum.USER_NO_FOUND, id);
		}
		
		if(CollectionUtils.isNotEmpty(user.getUsuarioRoles())){
			
			boolean isAdmin = user.getUsuarioRoles().stream()
			.filter(ur -> ur.getRole().getNombre().equals(Constantes.ROL_ADMIN_NAME)).count() > 0;
			
			if(isAdmin){
				throw new ValidationException(ErrorCodeEnum.USER_IS_ADMIN, user.getEmail());
			}
			
			
			userRoleRepository.delete(user.getUsuarioRoles());
		}
		
		
		List<Compra> compras = compraRepository.findComprasOfUsuario(id);
		
		if(CollectionUtils.isNotEmpty(compras)){			
			compraRepository.delete(compras);
		}
		
		this.userRepository.delete(id);
		
	}

	
    
}
