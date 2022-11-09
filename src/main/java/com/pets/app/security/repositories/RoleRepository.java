package com.pets.app.security.repositories;

import com.pets.app.security.enums.RoleName;
import com.pets.app.security.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Integer>{
	
	Optional<RoleModel> findByRoleName(RoleName roleName);
	
	
}
