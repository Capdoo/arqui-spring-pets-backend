package com.pets.app.modules.pets;

import com.pets.app.files.FileUploadService;
import com.pets.app.modules.details.DetailModel;
import com.pets.app.modules.details.DetailRepository;
import com.pets.app.modules.owners.OwnerModel;
import com.pets.app.modules.owners.OwnerRepository;
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
	
	public void savePet(PetDTO petDTO) throws IOException {

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

		OwnerModel ownerPet = ownerRepository.findById(petDTO.getIdOwner()).get();
		newPet.setOwner(ownerPet);

			if(petDTO.getSpecificBreed() == null) {
				DetailModel petDetail = detailRepository.findBySpeciesAndBreed(
						petDTO.getSpecies(),
						petDTO.getBreed()).get();
				newPet.setDetail(petDetail);
				newPet.setSpecificBreed(null);
	
			}else {
				newPet.setSpecificBreed(petDTO.getSpecies()+"#"+petDTO.getSpecificBreed());
				newPet.setDetail(null);
			}

		newPet.setShelter(null);
		
				String encoded = fileUploadService.obtenerEncoded(petDTO.getEncoded());
				byte[] imagen = fileUploadService.convertStringToBytes(encoded);
				String url = fileUploadService.fileUpload(imagen);

		newPet.setLinkImg(url);
			
		petRepository.save(newPet);
	}
	
	//Lista general
	public List<PetDTO> listAllPets(){
		List<PetDTO> listPets = new ArrayList<>();
		List<PetModel> listDB = petRepository.findAll();
		
		for(PetModel p : listDB) {
			FechaUtil fechaUtil = new FechaUtil();
			StringUtil stringUtil = new StringUtil();
			PetDTO mascotaSingle = new PetDTO();

				mascotaSingle.setId(p.getId());
			   	mascotaSingle.setName(p.getName());
				mascotaSingle.setGender(p.getGender());
				
		   			String fechaNacimiento = fechaUtil.convertirFecha(p.getBirthDate());
		   			mascotaSingle.setBirthDate(fechaNacimiento);
				
			   		String fechaRegistro = fechaUtil.convertirFecha(p.getBirthDate());
					mascotaSingle.setBirthDate(fechaRegistro);
					
				mascotaSingle.setColour(p.getColour());
				mascotaSingle.setCharacteristic(p.getCharacteristic());
				mascotaSingle.setSize(p.getSize());
				mascotaSingle.setIdOwner(p.getOwner().getId());

				if(p.getSpecificBreed()==null) {
					mascotaSingle.setSpecies(p.getDetail().getSpecies());
					mascotaSingle.setBreed(p.getDetail().getBreed());
					mascotaSingle.setSpecificBreed(null);
					mascotaSingle.setIdDetail(p.getDetail().getId());
				}else {
					mascotaSingle.setSpecies(stringUtil.obtenerEspecieToken(p.getSpecificBreed()));
					mascotaSingle.setBreed(null);
					mascotaSingle.setSpecificBreed(stringUtil.obtenerRazaToken(p.getSpecificBreed()));
				}
				
				mascotaSingle.setUrlLink(p.getLinkImg());

			listPets.add(mascotaSingle);
		}
		
		return listPets;
	}
	
	//Lista por id de dueño	
 	
}
