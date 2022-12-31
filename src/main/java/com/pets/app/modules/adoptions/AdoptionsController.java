package com.pets.app.modules.adoptions;

import com.pets.app.dto.MensajeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


@RestController
@RequestMapping("/adoptions")
public class AdoptionsController {

	@Autowired
	AdoptionService adopcionService;

	//@ApiIgnore
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/create")
	public ResponseEntity<Object> createAdoption(@RequestBody AdoptionDTO adoptionDTO){
		
		try {
			adopcionService.saveAdoption(adoptionDTO);
			return new ResponseEntity<Object>(new MensajeDTO("Adoption created successfully"), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MensajeDTO("There has been a problem"), HttpStatus.BAD_REQUEST);
		}
		
	}

	//@ApiIgnore
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/read")
	public ResponseEntity<Object> readAdoptions(){
		
		try {
			List<AdoptionDTO> listAdoptions = adopcionService.listAllAdoptions();
			return new ResponseEntity<Object>(listAdoptions, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MensajeDTO("There was a problem"), HttpStatus.BAD_REQUEST);
		}
	}

	//@ApiIgnore
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/read/pet")
	public ResponseEntity<Object> obtenerPorMascotaId(@RequestParam long id){
		
		try {
			List<AdoptionDTO> listAdoptions = adopcionService.getByPetId(id);
			return new ResponseEntity<Object>(listAdoptions, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MensajeDTO("There was a problem"), HttpStatus.BAD_REQUEST);
		}
	}

	//@ApiIgnore
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/read/single")
	public ResponseEntity<Object> readByUd(@RequestParam long id){
		
		try {
			AdoptionDTO adoption = adopcionService.getById(id);
			return new ResponseEntity<Object>(adoption, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MensajeDTO("There was a problem"), HttpStatus.BAD_REQUEST);
		}
		
	}
}
