package com.pets.app.modules.pets;

import com.pets.app.dto.MensajeDTO;
import com.pets.app.security.jwt.JwtProvider;
import com.pets.app.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pets")
public class PetController {
	@Autowired
    PetService petService;
	@Autowired
	JwtProvider jwtProvider;
	@Autowired
	UserService userService;

	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/create")
	public ResponseEntity<Object> createPet(@RequestBody PetDTO petDTO, @RequestHeader("Authorization") String token){
		String realToken = token.split(" ")[1];
		String username = jwtProvider.getNombreUsuarioFromToken(realToken);
		try {
			petService.savePet(petDTO, username);
			return new ResponseEntity<Object>(new MensajeDTO("Pet registered successfully"), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(new MensajeDTO("There has been a problem"), HttpStatus.BAD_REQUEST);
		}
	}
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/read")
	public ResponseEntity<Object> readPets(){
		try {
			List<PetDTO> listPets = petService.listAllPets();
			return new ResponseEntity<Object>(listPets, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MensajeDTO("There has been a problem"), HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/read-user")
	public ResponseEntity<Object> readPetsByUsername(@RequestHeader("Authorization") String token){
		String realToken = token.split(" ")[1];
		String username = jwtProvider.getNombreUsuarioFromToken(realToken);
		if(!userService.existsByUsernameOrEmail(username)){
			return new ResponseEntity<Object>(new MensajeDTO("User not found"), HttpStatus.BAD_REQUEST);
		}
		List<PetDTO> petDTOList = petService.getAllByUsername(username);
		return new ResponseEntity<Object>(petDTOList, HttpStatus.OK);
	}
	
}
