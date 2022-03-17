package com.tbk.exercise.restcontroller;

import static com.tbk.exercise.utils.ConstantUtil.LOG_END;
import static com.tbk.exercise.utils.ConstantUtil.LOG_START;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tbk.exercise.dto.LogInRequestDto;
import com.tbk.exercise.dto.ResponseDto;
import com.tbk.exercise.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * LogInControllerImpl - implements LogInController - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11.0.7
 */
@Slf4j
@RestController
@Api(tags = {"Security Authenticate"})
@RequestMapping("/security-authenticate")
public class LogInControllerImpl implements LogInController {

	
	
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
    public LogInControllerImpl(UserService userService) {
        this.userService = userService;
    }
	
	@Override
	@PostMapping("/login")
	@ApiOperation(value = "Autenticate", notes = "Returns the data referent user autenticated")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful return")})
	public ResponseEntity<ResponseDto> authUser(@Valid @RequestBody LogInRequestDto req) throws IOException {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		ResponseDto result = userService.logInUser(req);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return ResponseEntity.ok(result);
	}
	
}
