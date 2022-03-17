package com.tbk.exercise;

import static com.tbk.exercise.utils.ConstantUtil.LOG_END;
import static com.tbk.exercise.utils.ConstantUtil.LOG_START;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tbk.exercise.dto.PhoneRequestDto;
import com.tbk.exercise.dto.ResponseDto;
import com.tbk.exercise.dto.UserRequestDto;
import com.tbk.exercise.handler.MailFoundException;
import com.tbk.exercise.restcontroller.RegisterControllerImpl;
import com.tbk.exercise.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class RegisterControllerImplTest {

	@InjectMocks
	private RegisterControllerImpl controller;

	@Mock
	private UserService service;

	@Test
	public void registerUserTest() throws IOException, MailFoundException {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		
		ResponseEntity<ResponseDto> responseEntity;
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        
        UserRequestDto requestDto = new UserRequestDto("jesus", "test@test.cl", "123", Arrays.asList(new PhoneRequestDto("12","1245","124578")));
        
        when(service.registerUser(requestDto)).thenReturn(new ResponseDto("asdasdasd", "12", new Date(), new Date(), new Date(), false));
        responseEntity = controller.registerUser(requestDto);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getId()).isEqualTo("12");
    
        log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
	}

}
