package com.tbk.exercise.restcontroller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tbk.exercise.dto.UserDto;


/**
 * UserController - Users Services - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11.0.7
 */
public interface UserController {


	/**
	 * User List - method get list of users- Spring Boot
	 *
	 * @param none
	 * @return ResponseEntity<List<UserDto>> see {@link UserDto} 
	 */
	public ResponseEntity<List<UserDto>> getListOfUsers()  ;
	
	
}
