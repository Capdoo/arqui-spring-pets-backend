package com.pets.app.modules.pets;

import com.pets.app.dto.MensajeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pets")
public class PetController {
	@Autowired
    PetService petService;
	
	@PostMapping("/create")
	public ResponseEntity<Object> createPet(@RequestBody PetDTO petDTO){
		
		try {
			petService.savePet(petDTO);
			return new ResponseEntity<Object>(new MensajeDTO("Pet registered successfully"), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(new MensajeDTO("There has been a problem"), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/read")
	public ResponseEntity<Object> readPets(){
		
		try {
			List<PetDTO> listPets = petService.listAllPets();
			return new ResponseEntity<Object>(listPets, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MensajeDTO("There has been a problem"), HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
