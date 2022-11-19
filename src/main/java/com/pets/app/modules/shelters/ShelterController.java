package com.pets.app.modules.shelters;

import com.pets.app.dto.MensajeDTO;
import com.pets.app.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


@RestController
@RequestMapping("/shelters")

public class ShelterController {

	@Autowired
    ShelterService shelterService;

	@Autowired
	UserService userService;

	@ApiIgnore
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@PostMapping("/create")
	public ResponseEntity<Object> createShelter(@RequestBody ShelterDTO shelterDTO){
		
		try {
			shelterService.saveShelter(shelterDTO);
			return new ResponseEntity<Object>(new MensajeDTO("Shelter registered successfully"), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MensajeDTO("There has been a problem"), HttpStatus.BAD_REQUEST);
		}
		
	}

	@ApiIgnore
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@PostMapping("/create/partner")
	//TO DO
	public ResponseEntity<Object> createPartner(@RequestBody ShelterDTO shelterDTO){

		try {
			shelterService.saveShelter(shelterDTO);
			return new ResponseEntity<Object>(new MensajeDTO("Shelter registered successfully"), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MensajeDTO("There has been a problem"), HttpStatus.BAD_REQUEST);
		}

	}

	@ApiIgnore
	@GetMapping("/read")
	public ResponseEntity<Object> readShelters(){
		
		try {
			List<ShelterDTO> listShelters = shelterService.listAll();
			return new ResponseEntity<Object>(listShelters, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MensajeDTO("There has been a problem"), HttpStatus.BAD_REQUEST);
		}
		
	}


	@ApiIgnore
	@GetMapping("/read/single")
	public ResponseEntity<Object> readById(@RequestParam long id){


		try {
			ShelterDTO searchs = shelterService.getById(id);
			return new ResponseEntity<Object>(searchs, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MensajeDTO("There has been a problem"), HttpStatus.BAD_REQUEST);
		}

	}

	@ApiIgnore
	@GetMapping("/read/user")
	public ResponseEntity<Object> readByDniUser(@RequestParam String dni){

		if(!userService.existsByDni(dni)){
			return new ResponseEntity<Object>(new MensajeDTO("User not found"), HttpStatus.BAD_REQUEST);
		}

		List<ShelterDTO> listShelters = shelterService.getByDniUser(dni);
		return new ResponseEntity<Object>(listShelters, HttpStatus.OK);
	}
	

}
