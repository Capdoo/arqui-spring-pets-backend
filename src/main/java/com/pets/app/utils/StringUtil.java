package com.pets.app.utils;

public class StringUtil {
	
	private String data;
	
	
	public StringUtil() {
		super();
	}

	public void dividirCadenaToken() {
		String[] datos = "hola mundo como estas".split(" ");

		for(String item : datos)
		{
		  System.out.println(item);
		}
		
	}
	
	
	//ParaMascotas
	public String obtenerEspecieToken(String concatenado) {
		String[] datos = concatenado.split("#");
		return datos[0];
	}
	
	public String obtenerRazaToken(String concatenado) {
		String[] datos = concatenado.split("#");
		return datos[1];
	}

}
