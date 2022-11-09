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
	
	//No existe registrar, son datos fijos
	
	//Obtener todos
	public List<DetailDTO> listAll(){
		List<DetailDTO> listaDetalles = new ArrayList<>();
		List<DetailModel> detallesBD = detailRepository.findAll();
		

		for(DetailModel p : detallesBD) {
			//Single
			DetailDTO detalleSingle = new DetailDTO();
				detalleSingle.setId(p.getId());
				detalleSingle.setSpecies(p.getSpecies());
				detalleSingle.setBreed(p.getBreed());
			
			listaDetalles.add(detalleSingle);
		}
		
		return listaDetalles;
	}
	
	public List<StringDTO> getAllSpecies(){
		List<StringDTO> listaEspecies = new ArrayList<>();
		List<DetailModel> detallesBD = detailRepository.findAll();
		
		
		for(DetailModel p : detallesBD) {
			StringDTO stringDTOSingle = new StringDTO();
			
			if(!(existsSpeciesInList(listaEspecies, p.getSpecies()))) {
				stringDTOSingle.setData(p.getSpecies());
				
				listaEspecies.add(stringDTOSingle);
			}
		}
		
		return listaEspecies;
	}
	
	public boolean existsSpeciesInList(List<StringDTO> listaEspecies, String especie) {

		boolean res = false;
		
		for(StringDTO p : listaEspecies) {
			if(especie.equals(p.getData().toString())) {
				res = true;
			}
		}
		return res;
	}
	
	//Obtener Razas Por Especie
	public List<StringDTO> getBreedsBySpecies(String especie){
		List<StringDTO> listaRazas = new ArrayList<>();

		List<DetailModel> detallesBD = detailRepository.findAllBySpecies(especie);
		
		for(DetailModel p : detallesBD) {
			StringDTO stringSingle = new StringDTO();
				stringSingle.setData(p.getBreed());
			listaRazas.add(stringSingle);
		}
		
		return listaRazas;
	}
	
	
}










