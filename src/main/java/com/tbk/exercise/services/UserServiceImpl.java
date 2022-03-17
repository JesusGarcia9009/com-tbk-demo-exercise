package com.tbk.exercise.services;

import static com.tbk.exercise.utils.ConstantUtil.LOG_END;
import static com.tbk.exercise.utils.ConstantUtil.LOG_START;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tbk.exercise.dto.UserRequestDto;
import com.tbk.exercise.models.UserModel;
import com.tbk.exercise.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * UsersServiceImpl class that implements the service interface
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PhoneService phoneService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public UserModel getUserByEmail(String email) {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		UserModel result = null;
		Optional<UserModel> opt = userRepository.findByEmail(email);
		if(opt.isPresent())
			result = opt.get();
		
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public Long countUserByEmail(String email) {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		Long result = userRepository.countByEmail(email);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public Boolean existUserByEmail(String email) {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		Boolean result = userRepository.existsByEmail(email);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	@Transactional
	public UserModel saveUser(UserRequestDto dto) {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		Date now = new Date();
		UserModel model = new UserModel();
		model.setName(dto.getName());
		model.setEmail(dto.getEmail());
		model.setPassword(encoder.encode(dto.getPassword()));
		model.setCreated(now);
		model.setLastLogin(now);
		model.setModified(now);
		model.setActive(true);
		model.setLastToken("");
		
		model = userRepository.save(model);
		phoneService.saveAll(dto.getPhones(), model);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return model;
	}

	@Override
	public UserModel updateUser(UserModel model) {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		model.setLastLogin(new Date());
		model = userRepository.save(model);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return model;
	}

}
