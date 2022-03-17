package com.tbk.exercise.restcontroller;

import static com.tbk.exercise.utils.ConstantUtil.LOG_END;
import static com.tbk.exercise.utils.ConstantUtil.LOG_START;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tbk.exercise.config.JwtProvider;
import com.tbk.exercise.dto.LogInRequestDto;
import com.tbk.exercise.dto.ResponseDto;
import com.tbk.exercise.models.UserModel;
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

	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private UserService userService;
	
	@Override
	@PostMapping("/login")
	@ApiOperation(value = "Autenticate", notes = "Returns the data referent user autenticated")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful return")})
	public ResponseEntity<ResponseDto> authUser(@Valid @RequestBody LogInRequestDto req) throws IOException {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		Authentication sigin = null;
		sigin = jwtProvider.createToken(req.getEmail(), req.getPassword());
		
		ResponseDto autPass = new ResponseDto();
		String token = jwtProvider.generateToken(sigin);
		autPass.setToken(token);
		
		UserModel model = userService.getUserByEmail(req.getEmail());
		autPass.setActive(model.isActive());
		autPass.setCreated(model.getCreated());
		autPass.setId(model.getId().toString());
		autPass.setLastLogin(model.getLastLogin());
		autPass.setModified(model.getModified());
		model.setLastToken(token);
		userService.updateUser(model);

		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(autPass, HttpStatus.OK);
	}
	
}
