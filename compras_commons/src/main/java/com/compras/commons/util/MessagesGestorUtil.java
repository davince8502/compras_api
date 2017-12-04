package com.compras.commons.util;

import java.text.MessageFormat;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.compras.commons.emuns.ErrorCodeEnum;

@Component
public class MessagesGestorUtil implements MessageSourceAware {

	private MessageSource messageSource;

	/**
	 * El metodo setMessageSource permite manejar el objeto que contiene el
	 * origen de los mensajes para el manejo del lenguaje.
	 */
	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String getMessage(String key, Object... parameters) {
		if (messageSource != null) {
			return messageSource.getMessage(key, parameters, LocaleContextHolder.getLocale());
		}
		return ErrorCodeEnum.GENERAL_ERROR.getCode();
	}

	public String getMessage(String key) {
		if (messageSource != null) {
			return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
		}
		return ErrorCodeEnum.GENERAL_ERROR.getCode();
	}

	/**
	 * Metodo encargado de agregar valores a un mensaje mediante el uso de
	 * {@link MessageFormat}
	 * 
	 * @param String
	 *            mensaje
	 * @param Objct[]
	 *            args
	 * @return String mensaje
	 */
	public String agregarArgumentosMensaje(String mensaje, Object... args) {

		mensaje = MessageFormat.format(mensaje, args);
		return mensaje;
	}

}
