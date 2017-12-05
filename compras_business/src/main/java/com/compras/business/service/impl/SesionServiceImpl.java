package com.compras.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compras.business.repository.SesionRepository;
import com.compras.business.service.SesionService;
import com.compras.domain.model.Sesion;


@Service
public class SesionServiceImpl implements SesionService {
	
	@Autowired
	private SesionRepository sesionRepository;
	



	@Override
	public void saveSesion(Sesion session) {

		sesionRepository.save(session);
		
	}
	
	
	
	

}
