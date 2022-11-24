package com.pets.app.security.repositories;

import com.pets.app.security.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{

	Optional<UserModel> findByUsername(String username);
	Optional<UserModel> findByUsernameOrEmail(String username, String email);
	Optional<UserModel> findById(long id);
	Optional<UserModel> findByDni(String dni);
	Optional<UserModel> findByTokenPassword(String tokenPassword);

	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
	boolean existsByUsernameOrEmail(String username, String email);
	boolean existsByDni(String dni);
	
}
