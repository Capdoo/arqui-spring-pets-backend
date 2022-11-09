package com.pets.app.modules.adoptions;

import com.pets.app.dto.MensajeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/adoptions")
public class AdoptionsController {

	@Autowired
	AdoptionService adopcionService;
	
	@PostMapping("/create")
	public ResponseEntity<Object> createAdoption(@RequestBody AdoptionDTO adoptionDTO){
		
		try {
			adopcionService.saveAdoption(adoptionDTO);
			return new ResponseEntity<Object>(new MensajeDTO("Adoption created successfully"), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MensajeDTO("There has been a problem"), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/read")
	public ResponseEntity<Object> readAdoptions(){
		
		try {
			List<AdoptionDTO> listaAdopciones = adopcionService.listAllAdoptions();
			return new ResponseEntity<Object>(listaAdopciones, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MensajeDTO("Hubo un problema"), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/read/pet")
	public ResponseEntity<Object> obtenerPorMascotaId(@RequestParam long id){
		
		try {
			List<AdoptionDTO> listaAdopciones = adopcionService.getByPetId(id);
			return new ResponseEntity<Object>(listaAdopciones, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MensajeDTO("Hubo un problema"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/read/single")
	public ResponseEntity<Object> readByUd(@RequestParam long id){
		
		try {
			AdoptionDTO adopcion = adopcionService.getById(id);
			return new ResponseEntity<Object>(adopcion, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MensajeDTO("Hubo un problema"), HttpStatus.BAD_REQUEST);
		}
		
	}
}
