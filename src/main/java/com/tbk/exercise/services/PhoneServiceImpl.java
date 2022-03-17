package com.tbk.exercise.services;

import static com.tbk.exercise.utils.ConstantUtil.LOG_END;
import static com.tbk.exercise.utils.ConstantUtil.LOG_START;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tbk.exercise.dto.PhoneRequestDto;
import com.tbk.exercise.models.PhoneModel;
import com.tbk.exercise.models.UserModel;
import com.tbk.exercise.repository.PhoneRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * PhoneServiceImpl class that implements the service interface
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Slf4j
@Service
public class PhoneServiceImpl implements PhoneService {
	
	@Autowired
	private PhoneRepository phoneRepository;
	
	@Override
	public List<PhoneModel> saveAll(List<PhoneRequestDto> list, UserModel user) {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<PhoneModel> phonesList = new ArrayList<PhoneModel>();
		for (PhoneRequestDto item : list) {
			PhoneModel phone = new PhoneModel();
			phone.setCountryCode(item.getContrycode());
			phone.setNumber(item.getNumber());
			phone.setCityCode(item.getCitycode());
			phone.setUserPhone(user);
			phonesList.add(phone);
		}
		phoneRepository.saveAll(phonesList);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return phonesList;
	}
	
	
}
