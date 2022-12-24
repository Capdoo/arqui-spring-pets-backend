package com.pets.app.modules.details;

import com.pets.app.dto.StringDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailService {

	@Autowired
    DetailRepository detailRepository;

	public List<DetailDTO> listAll(){
		List<DetailDTO> listDetails = new ArrayList<>();
		List<DetailModel> detailsBD = detailRepository.findAll();
		for(DetailModel p : detailsBD) {
			//Single
			DetailDTO detailsSingle = new DetailDTO();
			detailsSingle.setId(p.getId());
			detailsSingle.setSpecies(p.getSpecies());
				detailsSingle.setBreed(p.getBreed());
			listDetails.add(detailsSingle);
		}
		return listDetails;
	}
	
	public List<StringDTO> getAllSpecies(){
		List<StringDTO> listSpecies = new ArrayList<>();
		List<DetailModel> detailsBD = detailRepository.findAll();
		for(DetailModel p : detailsBD) {
			StringDTO stringDTOSingle = new StringDTO();
			if(!(existsSpeciesInList(listSpecies, p.getSpecies()))) {
				stringDTOSingle.setData(p.getSpecies());
				listSpecies.add(stringDTOSingle);
			}
		}
		return listSpecies;
	}
	
	public boolean existsSpeciesInList(List<StringDTO> listSpecies, String species) {
		boolean res = false;
		for(StringDTO p : listSpecies) {
			if(species.equals(p.getData().toString())) {
				res = true;
			}
		}
		return res;
	}
	
	//Get Breeds By Species
	public List<StringDTO> getBreedsBySpecies(String especie){
		List<StringDTO> listBreeds = new ArrayList<>();
		List<DetailModel> detailsBD = detailRepository.findAllBySpecies(especie);
		for(DetailModel p : detailsBD) {
			StringDTO stringSingle = new StringDTO();
				stringSingle.setData(p.getBreed());
			listBreeds.add(stringSingle);
		}
		return listBreeds;
	}
}










