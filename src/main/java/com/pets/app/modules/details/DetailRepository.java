package com.pets.app.modules.details;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface DetailRepository extends JpaRepository<DetailModel, Long>{

	public List<DetailModel> findAll();
	public List<DetailModel> findAllBySpecies(String species);
	public Optional<DetailModel> findBySpeciesAndBreed(String species, String breed);
	
}
