package com.pets.app.dto;

public class MensajeDTO {

	public MensajeDTO(String mensaje) {
		super();
		this.mensaje = mensaje;
	}
	
	private String mensaje;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
