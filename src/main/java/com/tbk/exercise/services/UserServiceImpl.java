package com.tbk.exercise.services;

import static com.tbk.exercise.utils.ConstantUtil.LOG_END;
import static com.tbk.exercise.utils.ConstantUtil.LOG_START;
import static com.tbk.exercise.utils.ConstantUtil.MAIL_FOUND;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tbk.exercise.config.JwtProvider;
import com.tbk.exercise.dto.LogInRequestDto;
import com.tbk.exercise.dto.ResponseDto;
import com.tbk.exercise.dto.UserDto;
import com.tbk.exercise.dto.UserRequestDto;
import com.tbk.exercise.handler.MailFoundException;
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
	
	/**
	 * Global variables
	 */
	private UserRepository userRepository;
	private PhoneService phoneService;
	private PasswordEncoder encoder;
	private JwtProvider jwtProvider;
	
	/**
	 *  Class constructor with @autowire annotation
	 *  
	 * @param UserRepository @see {@link UserRepository}
	 * @param PhoneService @see {@link PhoneService}
	 * @param PasswordEncoder @see {@link PasswordEncoder}
	 * @param JwtProvider @see {@link JwtProvider}
	 */
	@Autowired
    public UserServiceImpl(UserRepository userRepository, PhoneService phoneService, PasswordEncoder encoder, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.phoneService = phoneService;
        this.encoder = encoder;
        this.jwtProvider = jwtProvider;
    }
	
	@Override
	public ResponseDto registerUser(UserRequestDto dto) throws IOException, MailFoundException {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		ResponseDto result = null;
		if(!this.existUserByEmail(dto.getEmail())) {
			this.saveUser(dto);
			result = this.logInUser(new LogInRequestDto(dto.getEmail(), dto.getPassword()));
		}else {
			throw new MailFoundException(MAIL_FOUND);
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}
	
	@Override
	public ResponseDto logInUser(LogInRequestDto dto) throws IOException {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		ResponseDto result = null;
		Authentication sigin = null;
		sigin = jwtProvider.createToken(dto.getEmail(), dto.getPassword());
		
		if(Objects.nonNull(sigin)) {
			result = new ResponseDto();
			String token = jwtProvider.generateToken(sigin);
			
			UserModel model = this.getUserByEmail(dto.getEmail());
			result.setActive(model.isActive());
			result.setCreated(model.getCreated());
			result.setId(model.getId().toString());
			result.setLastLogin(model.getLastLogin());
			result.setModified(model.getModified());
			result.setToken(token);
			model.setLastToken(token);
			this.updateUser(model);
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}
	
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

	@Override
	public List<UserDto> getListOfUsers() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<UserDto> result = userRepository.getListOfUsers();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	

	

}
