package com.tbk.exercise.restcontroller;

import static com.tbk.exercise.utils.ConstantUtil.LOG_END;
import static com.tbk.exercise.utils.ConstantUtil.LOG_START;
import static com.tbk.exercise.utils.ConstantUtil.MAIL_FOUND;

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
import com.tbk.exercise.dto.UserRequestDto;
import com.tbk.exercise.handler.MailFoundException;
import com.tbk.exercise.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/security-register")
@Api(tags = {"Security register"})
public class RegisterControllerImpl implements RegisterController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private LogInController logInController;
	
	@Override
	@PostMapping("/register")
	@ApiOperation(value = "Register user", notes = "Returns the data referent user autenticated")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful return")})
	public ResponseEntity<ResponseDto> registerUser(@Valid @RequestBody(required = true) UserRequestDto req) throws IOException, MailFoundException {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		ResponseEntity<ResponseDto> result = null;
		if(!userService.existUserByEmail(req.getEmail())) {
			userService.saveUser(req);
			result = logInController.authUser(new LogInRequestDto(req.getEmail(), req.getPassword()));
		}else {
			throw new MailFoundException(MAIL_FOUND);
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}
	
}
