package com.pets.app.modules.details;

import com.pets.app.dto.MensajeDTO;
import com.pets.app.dto.StringDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/details")
public class DetailsController {

	@Autowired
	DetailService detailService;

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/read")
	public ResponseEntity<Object> readAll(){
		try {
			List<DetailDTO> listDetails = detailService.listAll();
			return new ResponseEntity<Object>(listDetails, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MensajeDTO("There has been a problem"), HttpStatus.BAD_REQUEST);
		}
	}


	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/read/species")
	public ResponseEntity<Object> readSpecies(){
		
		try {
			List<StringDTO> listaEspecies = detailService.getAllSpecies();
			return new ResponseEntity<Object>(listaEspecies, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MensajeDTO("Hubo un problema"), HttpStatus.BAD_REQUEST);
		}
		
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/read/breed")
	public ResponseEntity<Object> readBreedsBySpecies(@RequestParam("species") String especie){
		
		try {
			List<StringDTO> listaRazas = detailService.getBreedsBySpecies(especie);
			return new ResponseEntity<Object>(listaRazas, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MensajeDTO("Hubo un problema"), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
}











