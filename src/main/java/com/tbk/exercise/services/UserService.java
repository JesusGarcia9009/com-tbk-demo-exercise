package com.tbk.exercise.services;

import java.io.IOException;
import java.util.List;

import com.tbk.exercise.dto.LogInRequestDto;
import com.tbk.exercise.dto.ResponseDto;
import com.tbk.exercise.dto.UserDto;
import com.tbk.exercise.dto.UserRequestDto;
import com.tbk.exercise.handler.MailFoundException;
import com.tbk.exercise.models.UserModel;


/**
 * UsersService - Class that contains the methods of the User entity
 * 
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11.0.7
 */
public interface UserService {
	
	/**
	 * get user by field username
	 * 
	 * @return ResponseDto @see {@link ResponseDto}
	 * @param dto @see {@link UserRequestDto}
	 */
	public ResponseDto registerUser(UserRequestDto dto) throws IOException, MailFoundException; 
	
	/**
	 * autenticate user by user and pass
	 * 
	 * @param ResponseDto @see {@link ResponseDto}
	 * @return dto @see {@link LogInRequestDto}
	 */
	public ResponseDto logInUser(LogInRequestDto dto) throws IOException; 
	
	/**
	 * get user by field username
	 * 
	 * @param string username
	 * @return user @see {@link UserModel}
	 */
	public UserModel getUserByEmail(String email); 
	
	/**
	 * count users with email
	 * 
	 * @param string email
	 * @return Long - count number of users
	 */
	public Long countUserByEmail(String email) ;
	
	/**
	 * exist users with email
	 * 
	 * @param string email
	 * @return Boolean - exist users
	 */
	public Boolean existUserByEmail(String email) ;
	
	/**
	 * save user an list of phones
	 * 
	 * @param UserRequestDto dto @see {@link UserRequestDto}
	 * @return ResponseDto - @see {@link ResponseDto}
	 */
	public UserModel saveUser(UserRequestDto dto) ;
	
	/**
	 * update user model
	 * 
	 * @param model dto @see {@link UserModel}
	 * @return UserModel - @see {@link UserModel}
	 */
	public UserModel updateUser(UserModel model) ;
	
	/**
	 * User List - method get list of users- Spring Boot
	 * 
	 * @param none
	 * @return List<UserDto> - @see {@link UserDto}
	 */
	public List<UserDto> getListOfUsers() ;
	
}
