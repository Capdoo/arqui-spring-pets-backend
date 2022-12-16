package com.pets.app.utils;

import com.pets.app.modules.details.DetailModel;
import com.pets.app.modules.details.DetailRepository;
import com.pets.app.security.enums.RoleName;
import com.pets.app.security.models.RoleModel;
import com.pets.app.security.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Component
public class CreateRoles implements CommandLineRunner{

	@Autowired
	RoleService roleService;
	@Autowired
	DetailRepository detailRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		RoleModel rolAdmin = new RoleModel(RoleName.ROLE_ADMIN);
		RoleModel rolUser = new RoleModel(RoleName.ROLE_USER);
		roleService.save(rolAdmin);
		roleService.save(rolUser);

		String[] listBreedsCats = new String[]{
				"Abisino"
				,"Americano de pelo duro",
				"Asiático",
				"Azul ruso",
				"Balinés",
				"Bengalí","Birmano",
				"Bobtail japonés de pelo corto",
				"Bobtail japonés de pelo largo"
		};

		String[] listBreedsDogs = new String[]{
				"",""
		};

		List<DetailModel> listDetails = new ArrayList<>();
		for(String p:listBreedsCats){
			listDetails.add(new DetailModel("gato", p));
		}
		for(String q:listBreedsDogs){
			listDetails.add((new DetailModel("dog", q)));
		}

		detailRepository.saveAll(listDetails);

	}
}
