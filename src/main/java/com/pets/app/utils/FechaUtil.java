package com.pets.app.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FechaUtil {
private int id;
	
	public FechaUtil() {
		super();
	}

	public FechaUtil(int id) {
		super();
		this.id = id;
	}

	//Convertir TimeStamp en fecha String
	
	public Timestamp obtenerTimeStampDeFecha(String fecha) {
		
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    Date parsedDate;
		try {
			parsedDate = dateFormat.parse(fecha);
		    Timestamp timestamp = new Timestamp(parsedDate.getTime());
			return timestamp;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}


	}
	
	public String convertirFecha(Timestamp mytimestamp) {
		return new SimpleDateFormat("dd/MM/yyyy").format(mytimestamp);
	}


}
