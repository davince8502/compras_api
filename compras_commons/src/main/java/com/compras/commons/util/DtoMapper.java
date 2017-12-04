package com.compras.commons.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper extends ModelMapper {	
	
	public DtoMapper() {		
		this.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

}
