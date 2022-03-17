package com.tbk.exercise.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tbk.exercise.dto.UserDto;
import com.tbk.exercise.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer>{
	
	boolean existsByEmail(String email);
	
	Long countByEmail(String email);
	
	Optional<UserModel> findByEmail(String email);
	
	@Query(   "   SELECT new com.tbk.exercise.dto.UserDto(u.id, u.name, u.email, u.password, u.created, u.modified, u.lastLogin, u.lastToken, u.active) " 
			+ "     FROM UserModel u ")
	List<UserDto> getListOfUsers();

}
