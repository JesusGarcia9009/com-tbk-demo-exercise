package com.tbk.exercise.restcontroller;

import static com.tbk.exercise.utils.ConstantUtil.LOG_END;
import static com.tbk.exercise.utils.ConstantUtil.LOG_START;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tbk.exercise.dto.UserDto;
import com.tbk.exercise.services.UserService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user-services")
@Api(tags = {"User Services"})
public class UserControllerImpl implements UserController {

	/**
	 * Global variables
	 */
	private UserService userService;
	
	/**
	 *  Class constructor with @autowire annotation
	 *  
	 * @param UserService @see {@link UserService}
	 */
	@Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }
	
	@Override
	@GetMapping("/list")
	public ResponseEntity<List<UserDto>> getListOfUsers() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<UserDto> result = userService.getListOfUsers();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(result, result == null || result.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
	}
	
}
