package com.compras.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.compras.commons.anotations.FieldValidate;
import com.compras.commons.contants.Constantes;
import com.compras.commons.exception.ValidationException;
import com.compras.domain.dto.FieldErrorDTO;
import com.compras.domain.model.Usuario;


@Component
public class ValidationUtils {
	
	
	/**
	 * Metodo encargado mediante Reflect de validar los campos de una entidad u
	 * Objeto cuyos campos esten marcados con la anotacion FieldValidate
	 * 
	 * @param Object target
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws Exception
	 */
	public List<FieldErrorDTO> validate(Object target, String nameEntity) throws ValidationException, IllegalAccessException {
		
		Class<?> clazz = target.getClass();
		
		List<FieldErrorDTO> listaErrores = new ArrayList<>();

		// validación campos clase tarjet
		Field[] fields = clazz.getDeclaredFields();	

		for (Field field : fields) {
			
			validateField(field, target, listaErrores, nameEntity);
			
			if(field.getType().getPackage() != null && field.getType().getPackage().getName().equals(Usuario.class.getPackage().getName())){
				
				field.setAccessible(true);
				
				if(field.get(target) != null){
					
					listaErrores.addAll(validate(field.get(target), field.getType().getSimpleName()));
					
					if(CollectionUtils.isNotEmpty(listaErrores)){
						return listaErrores;
					}
				}				

			}
		}
		
		return listaErrores;

	}
	
	
	public void validateField(Field element, Object target, List<FieldErrorDTO> listaErrores, String nameEntity) throws ValidationException, IllegalAccessException {

		element.setAccessible(true);
		Object value = element.get(target);

		
		if (element.isAnnotationPresent(FieldValidate.class)) {

			FieldValidate fv = element.getAnnotation(FieldValidate.class);
			
			// validación si una propiedad es requerida		
			if (fv.required() &&  
					((value == null)? true :
						value.getClass().isArray()? Array.getLength(value) == 0 :
							StringUtils.isBlank(value.toString()))) {
				
				listaErrores.add(new FieldErrorDTO(element.getName(), Constantes.ERROR_VALIDACION_CAMPO_REQUERIDO, nameEntity));
				return;				
			}
			
			if(value != null){
				
				// validación tamaño de la palabra
				if(value.toString().length() >	fv.maximo()){
					listaErrores.add(new FieldErrorDTO(element.getName(), Constantes.ERROR_VALIDACION_CAMPO_MAX_TAMANO, nameEntity));
					return;
				}	
				
				// validación si es un numero mayor a 0
				if(value instanceof Number && fv.positivo()){
					
					if (!Pattern.matches(Constantes.REGEX_NUM_POSITIVE, value.toString())) {
						listaErrores.add(new FieldErrorDTO(element.getName(), Constantes.ERROR_VALIDACION_CAMPO_INT_POSITIVO, nameEntity));
						return;
					}
				}				
			}			
		}	
	}
	
	
	
	static String splitCamelCase(String s) {
		return s.replaceAll(String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])",
				"(?<=[A-Za-z])(?=[^A-Za-z])"), " ");
	}
	
	
	static String capitalizeFirstLetter(String original) {
	    if (original == null || original.length() == 0) {
	        return original;
	    }
	    return original.substring(0, 1).toUpperCase() + original.substring(1);
	}
	
	static String lowerCaseFirstLetter(String original) {
	    if (original == null || original.length() == 0) {
	        return original;
	    }
	    return original.substring(0, 1).toLowerCase() + original.substring(1);
	}
	
	
}
