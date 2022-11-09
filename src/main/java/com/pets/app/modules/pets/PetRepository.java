package com.pets.app.modules.pets;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PetRepository extends JpaRepository<PetModel, Long>{

	public List<PetModel> findAll();
	
}
