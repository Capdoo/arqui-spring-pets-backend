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

	public void saveSearch(SearchDTO searchDTO) throws IOException {
		FechaUtil fechaUtil = new FechaUtil();
		PetModel selectedPet = petRepository.findById(searchDTO.getPetId()).get();
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
			String encoded = fileUploadService.obtenerEncoded(searchDTO.getEncoded());
			byte[] image = fileUploadService.convertStringToBytes(encoded);
			String url = fileUploadService.fileUpload(image);
			newSearch.setLinkImg(url);
		searchRepository.save(newSearch);
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
				busquedaSingle.setPetId(p.getPet().getId());
				busquedaSingle.setPhoneA(p.getPhoneA());
				busquedaSingle.setPhoneB(p.getPhoneB());
				busquedaSingle.setMessage(p.getMessage());
				//Nuevo: Nombre y raza (especie)
				busquedaSingle.setNamePet(p.getPet().getName());
				busquedaSingle.setSpeciesPet(p.getPet().getDetail().getSpecies());
				busquedaSingle.setBreedPet(p.getPet().getDetail().getBreed());
				busquedaSingle.setUrlLink(p.getLinkImg());
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
				singleSearch.setPetId(p.getPet().getId());
				singleSearch.setPhoneA(p.getPhoneA());
				singleSearch.setPhoneB(p.getPhoneB());
				singleSearch.setMessage(p.getMessage());
				singleSearch.setUrlLink(p.getLinkImg());
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
			searchSingle.setPetId(p.getPet().getId());
			searchSingle.setPhoneA(p.getPhoneA());
			searchSingle.setPhoneB(p.getPhoneB());
			searchSingle.setMessage(p.getMessage());
			searchSingle.setUrlLink(p.getLinkImg());
		return searchSingle;
	}
}





























