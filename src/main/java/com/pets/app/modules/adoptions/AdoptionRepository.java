package com.pets.app.modules.adoptions;

import com.pets.app.modules.pets.PetModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AdoptionRepository extends JpaRepository<AdoptionModel, Long>{

	public List<AdoptionModel> findAll();
	public List<AdoptionModel> findAllByPet(PetModel petModel);

	public boolean existsByPet(PetModel petModel);
}
