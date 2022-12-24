package com.pets.app.modules.searchs;

import com.pets.app.files.FileUploadService;
import com.pets.app.modules.pets.PetModel;
import com.pets.app.modules.pets.PetRepository;
import com.pets.app.utils.FechaUtil;
import com.pets.app.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
	
	@Autowired
	SearchRepository searchRepository;
	@Autowired
	PetRepository petRepository;
	@Autowired
	FileUploadService fileUploadService;

	public SearchModel saveSearch(SearchDTO searchDTO) throws IOException {
		FechaUtil fechaUtil = new FechaUtil();
		PetModel selectedPet = petRepository.findById(searchDTO.getPet_id()).get();
		SearchModel newSearch = new SearchModel();
			newSearch.setAddress(searchDTO.getAddress());
			newSearch.setDistrict(searchDTO.getDistrict());
			Timestamp fechaPerdida = fechaUtil.obtenerTimeStampDeFecha(searchDTO.getLostDate());
			newSearch.setLostDate(fechaPerdida);
			newSearch.setRegisterDate(new Timestamp(System.currentTimeMillis()));
			newSearch.setPet(selectedPet);
			newSearch.setPhoneA(searchDTO.getPhoneA());
			newSearch.setPhoneB(searchDTO.getPhoneB());
			newSearch.setMessage(searchDTO.getMessage());

			//Image
			String encoded = fileUploadService.obtenerEncoded(searchDTO.getEncoded());
			byte[] image = fileUploadService.convertStringToBytes(encoded);
			newSearch.setImage(image);

		SearchModel searchModel = searchRepository.save(newSearch);
		return searchModel;
	}
	
	//Get All
	public List<SearchDTO> listAll(){
		List<SearchDTO> listSend = new ArrayList<>();
		List<SearchModel> listDB = searchRepository.findAll();
		for(SearchModel p : listDB) {
			FechaUtil fechaUtil = new FechaUtil();
			StringUtil stringUtil = new StringUtil();
			SearchDTO busquedaSingle = new SearchDTO();
				busquedaSingle.setId(p.getId());
				busquedaSingle.setAddress(p.getAddress());
				busquedaSingle.setDistrict(p.getDistrict());
					String fechaPerdida = fechaUtil.convertirFecha(p.getLostDate());
					busquedaSingle.setLostDate(fechaPerdida);
					String fechaRegistro = fechaUtil.convertirFecha(p.getRegisterDate());
					busquedaSingle.setRegisterDate(fechaRegistro);
				busquedaSingle.setPet_id(p.getPet().getId());
				busquedaSingle.setPhoneA(p.getPhoneA());
				busquedaSingle.setPhoneB(p.getPhoneB());
				busquedaSingle.setMessage(p.getMessage());
				//Nuevo: Nombre y raza (especie)
				busquedaSingle.setNamePet(p.getPet().getName());
				busquedaSingle.setSpeciesPet(p.getPet().getDetail().getSpecies());
				busquedaSingle.setBreedPet(p.getPet().getDetail().getBreed());
				busquedaSingle.setEncoded(fileUploadService.convertBytesToString(p.getImage()));
			listSend.add(busquedaSingle);
		}
		return listSend;
	}
	
	//Get pet_id
	public List<SearchDTO> getSearchByPetId(long petId){
		List<SearchDTO> sendList = new ArrayList<>();
		PetModel selectedPet = petRepository.findById(petId).get();
		List<SearchModel> listDB = searchRepository.findAllByPet(selectedPet);
		for(SearchModel p : listDB) {
			SearchDTO singleSearch = new SearchDTO();
			FechaUtil fechaUtil = new FechaUtil();
				singleSearch.setId(p.getId());
				singleSearch.setAddress(p.getAddress());
				singleSearch.setDistrict(p.getDistrict());
				String dateLoss = fechaUtil.convertirFecha(p.getLostDate());
				singleSearch.setLostDate(dateLoss);
				String dateRegistration = fechaUtil.convertirFecha(p.getRegisterDate());
				singleSearch.setRegisterDate(dateRegistration);
				singleSearch.setPet_id(p.getPet().getId());
				singleSearch.setPhoneA(p.getPhoneA());
				singleSearch.setPhoneB(p.getPhoneB());
				singleSearch.setMessage(p.getMessage());
				singleSearch.setEncoded(fileUploadService.convertBytesToString(p.getImage()));
			sendList.add(singleSearch);
		}
		return sendList;
	}
	
	//Get id
	public SearchDTO getSearchById(long id){
		SearchModel p = searchRepository.findById(id).get();
		SearchDTO searchSingle = new SearchDTO();
			FechaUtil fechaUtil = new FechaUtil();
			searchSingle.setId(p.getId());
			searchSingle.setAddress(p.getAddress());
			searchSingle.setDistrict(p.getDistrict());
			String dateLoss = fechaUtil.convertirFecha(p.getLostDate());
			searchSingle.setLostDate(dateLoss);
			String dateRegistration = fechaUtil.convertirFecha(p.getRegisterDate());
			searchSingle.setRegisterDate(dateRegistration);
			searchSingle.setPet_id(p.getPet().getId());
			searchSingle.setPhoneA(p.getPhoneA());
			searchSingle.setPhoneB(p.getPhoneB());
			searchSingle.setMessage(p.getMessage());
			searchSingle.setEncoded(fileUploadService.convertBytesToString(p.getImage()));
		return searchSingle;
	}
}





























