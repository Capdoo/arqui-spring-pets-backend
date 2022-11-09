package com.pets.app.utils;

import com.pets.app.security.enums.RoleName;
import com.pets.app.security.models.RoleModel;
import com.pets.app.security.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


//@Component
public class CreateRoles implements CommandLineRunner{

	
	@Autowired
	RoleService roleService;
	
	//@Override
	public void run(String... args) throws Exception {
		
		RoleModel rolAdmin = new RoleModel(RoleName.ROLE_ADMIN);
		RoleModel rolUser = new RoleModel(RoleName.ROLE_USER);
		RoleModel rolRept = new RoleModel(RoleName.ROLE_REPT);

		roleService.save(rolAdmin);
		roleService.save(rolUser);
		roleService.save(rolRept);

	}
}
