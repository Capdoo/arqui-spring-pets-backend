package com.pets.app.modules.searchs;

import com.pets.app.modules.pets.PetModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SearchRepository extends JpaRepository<SearchModel, Long>{
	
	public List<SearchModel> findAll();
	public List<SearchModel> findAllByPet(PetModel petModel);
	
}
