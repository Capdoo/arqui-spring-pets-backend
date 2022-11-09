package com.pets.app.modules.owners;

import com.pets.app.security.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends JpaRepository<OwnerModel, Long>{
	
	List<OwnerModel> findAll();

	Optional<OwnerModel> findByUser(UserModel usuarioModel);

}
