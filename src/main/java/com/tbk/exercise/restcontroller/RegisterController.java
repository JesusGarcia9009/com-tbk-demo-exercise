package com.tbk.exercise.restcontroller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;

import com.tbk.exercise.dto.ResponseDto;
import com.tbk.exercise.dto.UserRequestDto;
import com.tbk.exercise.handler.MailFoundException;


/**
 * RegisterController - Register Services - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11.0.7
 */
public interface RegisterController {


	/**
	 * User Registration - method to register users- Spring Boot
	 *
	 * @param UserRequestDto @see {@link UserRequestDto}
	 * @return ResponseEntity<ResponseDto> see {@link ResponseDto} authentication response object
	 */
	public ResponseEntity<ResponseDto> registerUser(UserRequestDto req) throws IOException, MailFoundException ;
	
	
}
