package com.pets.app.modules.pets;

import com.pets.app.files.FileUploadService;
import com.pets.app.modules.details.DetailModel;
import com.pets.app.modules.details.DetailRepository;
import com.pets.app.modules.owners.OwnerModel;
import com.pets.app.modules.owners.OwnerRepository;
import com.pets.app.security.models.UserModel;
import com.pets.app.security.repositories.UserRepository;
import com.pets.app.utils.FechaUtil;
import com.pets.app.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {

	@Autowired
    PetRepository petRepository;
	@Autowired
	OwnerRepository ownerRepository;
	@Autowired
	DetailRepository detailRepository;
	@Autowired
	FileUploadService fileUploadService;
	@Autowired
	UserRepository userRepository;
	
	public PetModel savePet(PetDTO petDTO, String username) throws IOException {

		FechaUtil fechaUtil = new FechaUtil();
		PetModel newPet = new PetModel();

		newPet.setName(petDTO.getName());
		newPet.setGender(petDTO.getGender());
				Timestamp timeStamp = fechaUtil.obtenerTimeStampDeFecha(petDTO.getBirthDate());
		newPet.setBirthDate(timeStamp);
		newPet.setRegisterDate(new Timestamp(System.currentTimeMillis()));
		newPet.setColour(petDTO.getColour());
		newPet.setCharacteristic(petDTO.getCharacteristic());
		newPet.setSize(petDTO.getSize());

		UserModel userModel = userRepository.findByUsername(username).get();
		OwnerModel ownerPet = ownerRepository.findByUser(userModel).get();
		newPet.setOwner(ownerPet);

		DetailModel petDetail = detailRepository.findBySpeciesAndBreed(
				petDTO.getSpecies(),
				petDTO.getBreed()).get();
		newPet.setDetail(petDetail);
		newPet.setShelter(null);


		String encoded = fileUploadService.obtenerEncoded(petDTO.getEncoded());
		byte[] image = fileUploadService.convertStringToBytes(encoded);

		newPet.setImage(image);
		return petRepository.save(newPet);
	}
	
	//List general
	public List<PetDTO> listAllPets(){
		List<PetDTO> listPets = new ArrayList<>();
		List<OwnerModel> listOwnerDb = ownerRepository.findAll();
		List<Long> listIdPetsByOwner = new ArrayList<>();

		for(OwnerModel p:listOwnerDb){
			for(PetModel q:p.getPets()){
				listIdPetsByOwner.add(q.getId());
			}
		}
		List<PetModel> petModelDb = petRepository.findAllById(listIdPetsByOwner);

		for(PetModel p : petModelDb) {
			FechaUtil fechaUtil = new FechaUtil();
			StringUtil stringUtil = new StringUtil();
			PetDTO petSingle = new PetDTO();

				petSingle.setId(p.getId());
				petSingle.setName(p.getName());
				petSingle.setGender(p.getGender());
				
				String dateNacimiento = fechaUtil.convertirFecha(p.getBirthDate());
				petSingle.setBirthDate(dateNacimiento);

				String dateRegistro = fechaUtil.convertirFecha(p.getBirthDate());
				petSingle.setBirthDate(dateRegistro);

				petSingle.setColour(p.getColour());
				petSingle.setCharacteristic(p.getCharacteristic());
				petSingle.setSize(p.getSize());
				petSingle.setIdOwner(p.getOwner().getId());

				petSingle.setSpecies(p.getDetail().getSpecies());
				petSingle.setBreed(p.getDetail().getBreed());
				petSingle.setIdDetail(p.getDetail().getId());

				petSingle.setEncoded(fileUploadService.convertBytesToString(p.getImage()));

			listPets.add(petSingle);
		}
		
		return listPets;
	}



	//Lista por username
	public List<PetDTO> getAllByUsername(String username){

		List<PetDTO> listSend = new ArrayList<PetDTO>();
		UserModel userModel = userRepository.findByUsername(username).get();
		OwnerModel ownerModel = ownerRepository.findByUser(userModel).get();
		List<PetModel> petModelList = ownerModel.getPets();

		FechaUtil fechaUtil = new FechaUtil();
		for(PetModel p:petModelList){
			PetDTO petDTO = new PetDTO();
				String birthDate = fechaUtil.convertirFecha(p.getBirthDate());
				petDTO.setBirthDate(birthDate);
				petDTO.setCharacteristic(p.getCharacteristic());
				petDTO.setColour(p.getColour());
				petDTO.setGender(p.getGender());
				petDTO.setEncoded(fileUploadService.convertBytesToString(p.getImage()));
				petDTO.setName(p.getName());
				String registerDate = fechaUtil.convertirFecha(p.getRegisterDate());
				petDTO.setRegisterDate(registerDate);
				petDTO.setSize(p.getSize());
				petDTO.setIdOwner(p.getOwner().getId());
				petDTO.setIdDetail(p.getDetail().getId());
			listSend.add(petDTO);
		}
		return listSend;
	}
}
