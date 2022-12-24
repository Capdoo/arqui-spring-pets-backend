package com.pets.app.modules.owners;

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
@RequestMapping("/owners")
public class OwnerControllers {

	@Autowired
    OwnerService ownerService;
	@Autowired
	UserService userService;
	@Autowired
	JwtProvider jwtProvider;

	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/create")
	public ResponseEntity<Object> createOwner(@RequestBody OwnerDTO ownerDTO, @RequestHeader("Authorization") String token){
		String realToken = token.split(" ")[1];
		String username = jwtProvider.getNombreUsuarioFromToken(realToken);
		try {
			if(!(userService.existsPorId((int) ownerDTO.getUser_id()))){
				int b = 11;
				return new ResponseEntity<Object>(new MensajeDTO("The user does not exists"), HttpStatus.BAD_REQUEST);
			}

 			if(ownerService.existsOwnerByUserId((int) ownerDTO.getUser_id())){
				int a = 10;
				return new ResponseEntity<Object>(new MensajeDTO("This user is already related to an owner"), HttpStatus.BAD_REQUEST);
			}

			int c = 12;

			OwnerModel ownerModel = ownerService.saveOwner(ownerDTO, username);
			return new ResponseEntity<Object>(new MensajeDTO("Registered successfully"), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MensajeDTO("There has been a problem"), HttpStatus.BAD_REQUEST);
		}
		
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/read")
	public ResponseEntity<Object> get(){
		
		try {
			List<OwnerDTO> listsOwner = ownerService.listAll();
			return new ResponseEntity<Object>(listsOwner, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(new MensajeDTO("There has been a problem"), HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
