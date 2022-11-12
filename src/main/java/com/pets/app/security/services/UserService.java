package com.pets.app.security.services;

import com.pets.app.modules.owners.OwnerRepository;
import com.pets.app.security.dto.UserDTO;
import com.pets.app.security.models.UserModel;
import com.pets.app.security.repositories.UserRepository;
import com.pets.app.utils.ModelDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//To implement rollbacks and avoid inconsistency: Concurrency
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OwnerRepository ownerRepository;

	@Autowired
	ModelDTOService modelDTOService;
	
	//Get
	public List<UserDTO> listar(){
		List<UserDTO> listaEnviar = new ArrayList<>();

		List<UserModel> listaModels = userRepository.findAll();
		
		for(UserModel p: listaModels) {
			UserDTO usuarioSingle = new UserDTO();

				usuarioSingle.setId(p.getId());
				usuarioSingle.setLastName(p.getLastName());
				usuarioSingle.setSurName(p.getSurName());
				usuarioSingle.setAddress(p.getAddress());
				usuarioSingle.setDni(p.getDni());
				usuarioSingle.setEmail(p.getEmail());
				usuarioSingle.setFirstName(p.getFirstName());
				usuarioSingle.setUsername(p.getUsername());
				usuarioSingle.setPhone(p.getPhone());
				
				
				usuarioSingle.setUrlLink(p.getLinkImg());
				
			listaEnviar.add(usuarioSingle);
		}

		return listaEnviar;
	}
	
	//Para el JWT
	public long obtenerIdPorUsername(String nombreUsuario) {
		UserModel usuarioModel =  userRepository.findByUsername(nombreUsuario).get();
		return usuarioModel.getId();
	}
	
	//Seguridad
	public Optional<UserModel> getByNombreUsuario(String nombreUsuario){
		return userRepository.findByUsername(nombreUsuario);
	}
	
	public boolean existsByNombreUsuario(String nombreUsuario) {
		return userRepository.existsByUsername(nombreUsuario);
	}
	
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
	
	public boolean existsPorId(long id) {
		return userRepository.existsById(id);
	}

	public boolean existsByDni(String dni){ return userRepository.existsByDni(dni);}

	public void save(UserModel usuarioModel) {
		userRepository.save(usuarioModel);
	}

	public UserDTO getById(long id){
		UserModel usuarioModel = userRepository.findById(id).get();
		return modelDTOService.getUserDTOfromModel(usuarioModel);
	}

	public void updateUser(int id, UserDTO userDTO){

		UserModel userModel = userRepository.findById(id).get();

			userModel.setLastName(userDTO.getLastName());
			userModel.setSurName(userDTO.getSurName());
			userModel.setFirstName(userDTO.getFirstName());

			userModel.setDni(userDTO.getDni());
			userModel.setEmail(userDTO.getEmail());
			userModel.setAddress(userDTO.getAddress());

			userModel.setUsername(userDTO.getUsername());
			userModel.setPhone(userDTO.getPhone());

		userRepository.save(userModel);

	}

	public void deleteUser(long id){

//		//Get the user
//		UserModel userModel = userRepository.findById(id).get();
//
//		//Si es dueno
//		if(ownerService.existsOwnerByUserId(userModel.getId())){
//			//Eliminar mascotas
//		}
//
//		//Si es propietario de un o unos refugios, eliminar todos
//		if(shelterService.existsRefugioByUserId(userModel.getId())){
//			//
//		}
//
//		userRepository.deleteById(id);
	}
	
}











