package com.pets.app.modules.shelters;

import com.pets.app.security.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ShelterRepository extends JpaRepository<ShelterModel, Long>{

	public List<ShelterModel> findAll();
	public List<ShelterModel> findAllByUser(UserModel userModel);

	//public boolean existsByUsuario();
}
