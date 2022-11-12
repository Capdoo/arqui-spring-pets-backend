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
		
		//Getting the User
		int user_id = (int) ownerDTO.getUser_id();
		UserModel userObtained = usuarioRepository.findById(user_id).get();
		
		OwnerModel OwnerModel = new OwnerModel();
			OwnerModel.setRegisterDate(new Timestamp(System.currentTimeMillis()));
			OwnerModel.setHistorial_id(ownerDTO.getHistorial_id());
			OwnerModel.setNumberOfPets(ownerDTO.getNumberOfPets());
			OwnerModel.setRate(ownerDTO.getRate());
			OwnerModel.setUser(userObtained);
			
		ownerRepository.save(OwnerModel);
	}
	
	public List<OwnerDTO> listAll(){
		List<OwnerDTO> sendList = new ArrayList<>();
		
		//fetch the data
		List<OwnerModel> listModelsBD = ownerRepository.findAll();

		for(OwnerModel p : listModelsBD) {
			FechaUtil fechaUtil = new FechaUtil();
			OwnerDTO ownerSingle = new OwnerDTO();
			String dateRegistration = fechaUtil.convertirFecha(p.getRegisterDate());

				ownerSingle.setId(p.getId());
				ownerSingle.setRegisterDate(dateRegistration);
				ownerSingle.setHistorial_id(p.getHistorial_id());
				ownerSingle.setNumberOfPets(p.getNumberOfPets());
				ownerSingle.setRate(p.getRate());
				ownerSingle.setUser_id(p.getUser().getId());

			sendList.add(ownerSingle);
		}
		
		return sendList;
	}
	
	//Useful
	public boolean existsOwnerByUserId(long idUser) {
		boolean res = false;
		
		if(userService.existsPorId(idUser)) {
			UserModel user = usuarioRepository.findById(idUser).get();
			
			Optional<OwnerModel> ownerSupposed = ownerRepository.findByUser(user);
			
			if(ownerSupposed.isPresent()) {
				res = true;
			}
		}
		return res;
	}

}





