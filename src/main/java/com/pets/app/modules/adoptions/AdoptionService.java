package com.pets.app.modules.adoptions;

import com.pets.app.modules.pets.PetModel;
import com.pets.app.modules.pets.PetRepository;
import com.pets.app.modules.searchs.SearchRepository;
import com.pets.app.utils.FechaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdoptionService {

	@Autowired
	SearchRepository searchRepository;
	
	@Autowired
	PetRepository petRepository;
	
	@Autowired
    AdoptionRepository adoptionRepository;

	public void saveAdoption(AdoptionDTO adoptionDTO) {
		
		FechaUtil fechaUtil = new FechaUtil();
		PetModel selectedPet = petRepository.findById(adoptionDTO.getPet_id()).get();
		
		AdoptionModel newAdoption = new AdoptionModel();
			newAdoption.setAddress(adoptionDTO.getAddress());
			newAdoption.setDistrict(adoptionDTO.getDistrict());

			newAdoption.setRegisterDate(new Timestamp(System.currentTimeMillis()));
			newAdoption.setPet(selectedPet);

			newAdoption.setPhoneA(adoptionDTO.getPhoneA());
			newAdoption.setPhoneB(adoptionDTO.getPhoneB());

			newAdoption.setMessage(adoptionDTO.getMessage());
			newAdoption.setPet(selectedPet);

			newAdoption.setObservation(adoptionDTO.getObservation());

		adoptionRepository.save(newAdoption);
	}
	
	//Get all
	public List<AdoptionDTO> listAllAdoptions(){
		List<AdoptionDTO> sendList = new ArrayList<>();
		List<AdoptionModel> listBD = adoptionRepository.findAll();

		
		for(AdoptionModel p : listBD) {
			FechaUtil fechaUtil = new FechaUtil();
			AdoptionDTO singleAdoption = new AdoptionDTO();

				singleAdoption.setId(p.getId());

				singleAdoption.setAddress(p.getAddress());
				singleAdoption.setDistrict(p.getDistrict());


				String registerDate = fechaUtil.convertirFecha(p.getRegisterDate());
				singleAdoption.setRegisterDate(registerDate);
				singleAdoption.setPet_id(p.getPet().getId());

				singleAdoption.setPhoneA(p.getPhoneA());
				singleAdoption.setPhoneB(p.getPhoneB());

				singleAdoption.setMessage(p.getMessage());
				singleAdoption.setObservation(p.getObservation());

			sendList.add(singleAdoption);
			
		}
		return sendList;
	}
	
	//get pet_id
	public List<AdoptionDTO> getByPetId(long petId){
		List<AdoptionDTO> sendList = new ArrayList<>();
		PetModel petselected = petRepository.findById(petId).get();
		
		List<AdoptionModel> listBD = adoptionRepository.findAllByPet(petselected);
		
		for(AdoptionModel p : listBD) {
			AdoptionDTO adoptionSingle = new AdoptionDTO();
			FechaUtil fechaUtil = new FechaUtil();

				adoptionSingle.setId(p.getId());
				
				adoptionSingle.setAddress(p.getAddress());
				adoptionSingle.setDistrict(p.getDistrict());

					
					String registerDate = fechaUtil.convertirFecha(p.getRegisterDate());
					adoptionSingle.setRegisterDate(registerDate);
					
				adoptionSingle.setPet_id(p.getPet().getId());
				adoptionSingle.setPhoneA(p.getPhoneA());
				
				adoptionSingle.setPhoneB(p.getPhoneB());
				adoptionSingle.setMessage(p.getMessage());
				
				adoptionSingle.setObservation(p.getObservation());
			sendList.add(adoptionSingle);
			
		}
		return sendList;
	}
	
	//get id
	public AdoptionDTO getById(long id){
		
		AdoptionModel p = adoptionRepository.findById(id).get();

		AdoptionDTO adoptionSingle = new AdoptionDTO();
		FechaUtil fechaUtil = new FechaUtil();

			adoptionSingle.setId(p.getId());
			
			adoptionSingle.setAddress(p.getAddress());
			adoptionSingle.setDistrict(p.getDistrict());

				
				String fechaRegistro = fechaUtil.convertirFecha(p.getRegisterDate());
				adoptionSingle.setRegisterDate(fechaRegistro);
				
			adoptionSingle.setPet_id(p.getPet().getId());
			adoptionSingle.setPhoneA(p.getPhoneA());
			
			adoptionSingle.setPhoneB(p.getPhoneB());
			adoptionSingle.setMessage(p.getMessage());
			
			adoptionSingle.setObservation(p.getObservation());

		return adoptionSingle;
	}

	public boolean existsAdoptionByPet(PetModel petModel){

		return adoptionRepository.existsByPet(petModel);

	}

}
