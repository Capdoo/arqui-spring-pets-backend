package com.pets.app.modules.owners;

import com.pets.app.security.models.UserModel;
import com.pets.app.security.repositories.UserRepository;
import com.pets.app.security.services.UserService;
import com.pets.app.utils.FechaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

	@Autowired
	UserRepository usuarioRepository;
	
	@Autowired
    OwnerRepository ownerRepository;
	
	@Autowired
	UserService userService;
	
	public void saveOwner(OwnerDTO ownerDTO) {
		
		//Obteniendo al Usuario
		int user_id = (int) ownerDTO.getUser_id();
		UserModel usuarioObtenido = usuarioRepository.findById(user_id).get();
		
		OwnerModel dueñoModel = new OwnerModel();
			dueñoModel.setRegisterDate(new Timestamp(System.currentTimeMillis()));
			dueñoModel.setHistorial_id(ownerDTO.getHistorial_id());
			dueñoModel.setNumberOfPets(ownerDTO.getNumberOfPets());
			dueñoModel.setRate(ownerDTO.getRate());
			dueñoModel.setUser(usuarioObtenido);
			
		ownerRepository.save(dueñoModel);
	}
	
	public List<OwnerDTO> listAll(){
		List<OwnerDTO> sendList = new ArrayList<>();
		
		//Traer los datos
		List<OwnerModel> listaModelsBD = ownerRepository.findAll();

		for(OwnerModel p : listaModelsBD) {
			FechaUtil fechaUtil = new FechaUtil();
			OwnerDTO duenoSingle = new OwnerDTO();
			String fechaRegistro = fechaUtil.convertirFecha(p.getRegisterDate());
			
				duenoSingle.setId(p.getId());
				duenoSingle.setRegisterDate(fechaRegistro);
				duenoSingle.setHistorial_id(p.getHistorial_id());
				duenoSingle.setNumberOfPets(p.getNumberOfPets());
				duenoSingle.setRate(p.getRate());
				duenoSingle.setUser_id(p.getUser().getId());

			sendList.add(duenoSingle);
		}
		
		return sendList;
	}
	
	//Util
	public boolean existsOwnerByUserId(long idUsuario) {
		boolean res = false;
		
		if(userService.existsPorId(idUsuario)) {
			UserModel usuario = usuarioRepository.findById(idUsuario).get();
			
			Optional<OwnerModel> duenoSupuesto = ownerRepository.findByUser(usuario);
			
			if(duenoSupuesto.isPresent()) {
				res = true;
			}
		}
		return res;
	}

}





