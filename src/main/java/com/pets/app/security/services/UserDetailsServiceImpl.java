package com.pets.app.security.services;

import com.pets.app.security.models.UserModel;
import com.pets.app.security.models.UsuarioPrincipalModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Converts the User class to the Main User.
//Average between the User class and the Main User.
//Is the SpringSecurity class specified
//To obtain the user data and its privileges

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
    UserService userService;
	
	
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserModel userModel = userService.getByUsernameOrEmail(usernameOrEmail).get();
		return UsuarioPrincipalModel.build(userModel);
	}

	
	
}
