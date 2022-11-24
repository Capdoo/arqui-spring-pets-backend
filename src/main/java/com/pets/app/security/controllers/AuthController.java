package com.pets.app.security.controllers;

import com.pets.app.dto.MensajeDTO;
import com.pets.app.files.FileUploadService;
import com.pets.app.security.dto.JwtDTO;
import com.pets.app.security.dto.LoginUserDTO;
import com.pets.app.security.dto.NewUserDTO;
import com.pets.app.security.enums.RoleName;
import com.pets.app.security.jwt.JwtProvider;
import com.pets.app.security.models.RoleModel;
import com.pets.app.security.models.UserModel;
import com.pets.app.security.services.RoleService;
import com.pets.app.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	JwtProvider jwtProvider;
	@Autowired
	FileUploadService fileUploadService;
	
	@PostMapping("/register")
	public ResponseEntity<Object> nuevo(@RequestBody NewUserDTO newUserDTO, BindingResult bindingResult) throws IOException{
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new MensajeDTO("Wrong fields"), HttpStatus.BAD_REQUEST);
		}
		if (userService.existsByNombreUsuario(newUserDTO.getUsername())) {
			return new ResponseEntity(new MensajeDTO("Username already in use"), HttpStatus.BAD_REQUEST);
		}
		if (userService.existsByEmail(newUserDTO.getEmail())) {
			return new ResponseEntity(new MensajeDTO("Email already in use"), HttpStatus.BAD_REQUEST);
		}
		UserModel usuarioModel = new UserModel();
			usuarioModel.setLastName(newUserDTO.getFirstName());
			usuarioModel.setSurName(newUserDTO.getSurName());
			usuarioModel.setFirstName(newUserDTO.getName());
			usuarioModel.setAddress(newUserDTO.getAddress());
			usuarioModel.setDni(newUserDTO.getDni());
			usuarioModel.setEmail(newUserDTO.getEmail());
			usuarioModel.setUsername(newUserDTO.getUsername());
			usuarioModel.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));
			usuarioModel.setPhone(newUserDTO.getPhone());
		Set<RoleModel> roles = new HashSet<>();
		roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());
		if (newUserDTO.getRoles().contains("admin")) {
			roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
		}
		if (newUserDTO.getRoles().contains("rept")) {
			roles.add(roleService.getByRoleName(RoleName.ROLE_REPT).get());
		}
//		String encoded = fileUploadService.obtenerEncoded(newUserDTO.getEncoded());
//		byte[] imagen = fileUploadService.convertStringToBytes(encoded);
//		String url = fileUploadService.fileUpload(imagen);
//		usuarioModel.setLinkImg(url);
		usuarioModel.setRoles(roles);
		userService.save(usuarioModel);
		return new ResponseEntity(new MensajeDTO("User registered successfully"), HttpStatus.CREATED);
	}
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginUserDTO loginUserDTO, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new MensajeDTO("Wrong fields"), HttpStatus.BAD_REQUEST);
		}
		if(!(userService.existsByUsernameOrEmail(loginUserDTO.getUsername()))) {
			return new ResponseEntity(new MensajeDTO("Wrong fields"), HttpStatus.BAD_REQUEST);
		}
        return Autenticacion(loginUserDTO.getUsername(), loginUserDTO.getPassword());
	}
	public ResponseEntity<Object> Autenticacion(String username, String password) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String jwt = jwtProvider.generateToken(authentication);
	        JwtDTO jwtDto = new JwtDTO(jwt);
	        return new ResponseEntity(jwtDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(new MensajeDTO("Wrong fields"), HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping("/refresh")
	public ResponseEntity<Object> refreshToken(@RequestBody JwtDTO jwtDTO) throws ParseException {
		try {
			String token = jwtProvider.refreshToken(jwtDTO);
			JwtDTO jwt = new JwtDTO(token);
			return new ResponseEntity<Object>(jwt, HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<Object>(new MensajeDTO(e.getMessage()), HttpStatus.OK);
		}
	}
}











