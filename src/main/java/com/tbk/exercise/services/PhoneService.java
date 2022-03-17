package com.tbk.exercise.services;

import java.util.List;

import com.tbk.exercise.dto.PhoneRequestDto;
import com.tbk.exercise.models.PhoneModel;
import com.tbk.exercise.models.UserModel;


/**
 * PhoneService - Class that contains the methods of the Phone entity
 * 
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11.0.7
 */
public interface PhoneService {

	
	/**
	 * save list of phones
	 * 
	 * @param none
	 * @return model @see User
	 */
	public List<PhoneModel> saveAll(List<PhoneRequestDto> list, UserModel user); 
	
}
