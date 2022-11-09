package com.pets.app.security.services;

import com.pets.app.security.enums.RoleName;
import com.pets.app.security.models.RoleModel;
import com.pets.app.security.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional

public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	public Optional<RoleModel> getByRoleName (RoleName roleName){
		return roleRepository.findByRoleName(roleName);
	}
	
	public void save(RoleModel roleModel) {
		roleRepository.save(roleModel);
	}
}
