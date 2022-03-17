package com.tbk.exercise.restcontroller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;

import com.tbk.exercise.dto.LogInRequestDto;
import com.tbk.exercise.dto.ResponseDto;


/**
 * LogInController - Log In Services Services - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11.0.7
 */
public interface LogInController {


	/**
	 * User Authentication - Method to sigin - Spring Boot
	 *
	 * @param dto LogInRequestDto see {@link LogInRequestDto}
	 * @return ResponseEntity<ResponseDto> see {@link ResponseDto} authentication response object
	 */
	public ResponseEntity<ResponseDto> authUser(LogInRequestDto req) throws IOException;
	
	
}
