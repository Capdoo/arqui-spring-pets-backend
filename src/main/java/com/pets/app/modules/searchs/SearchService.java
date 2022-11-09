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
				byte[] imagen = fileUploadService.convertStringToBytes(encoded);
				String url = fileUploadService.fileUpload(imagen);

		newSearch.setLinkImg(url);
			
			
		searchRepository.save(newSearch);
	}
	
	//Obtener todos
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
				if(p.getPet().getDetail() != null) {
					busquedaSingle.setSpeciesPet(p.getPet().getDetail().getSpecies());
					busquedaSingle.setBreedPet(p.getPet().getDetail().getBreed());
				}else {
					busquedaSingle.setSpeciesPet(stringUtil.obtenerEspecieToken(p.getPet().getSpecificBreed()));
					busquedaSingle.setBreedPet(stringUtil.obtenerRazaToken(p.getPet().getSpecificBreed()));
				}
				
				busquedaSingle.setUrlLink(p.getLinkImg());

			listSend.add(busquedaSingle);
			
		}
		return listSend;
	}
	
	//Obtener por mascota_id
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
						String fechaPerdida = fechaUtil.convertirFecha(p.getLostDate());
				singleSearch.setLostDate(fechaPerdida);
					
					String fechaRegistro = fechaUtil.convertirFecha(p.getRegisterDate());
				singleSearch.setRegisterDate(fechaRegistro);

				singleSearch.setPetId(p.getPet().getId());
				singleSearch.setPhoneA(p.getPhoneA());
				singleSearch.setPhoneB(p.getPhoneB());
				singleSearch.setMessage(p.getMessage());

				singleSearch.setUrlLink(p.getLinkImg());

			sendList.add(singleSearch);
			
		}
		return sendList;
	}
	
	//Obtener por id
	public SearchDTO getSearchById(long id){
		
		SearchModel p = searchRepository.findById(id).get();
		
		SearchDTO busquedaSingle = new SearchDTO();
			FechaUtil fechaUtil = new FechaUtil();

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
			
			busquedaSingle.setUrlLink(p.getLinkImg());

		return busquedaSingle;
	}
	
}





























