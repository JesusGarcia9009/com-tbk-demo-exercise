package com.tbk.exercise;

import static com.tbk.exercise.utils.ConstantUtil.LOG_END;
import static com.tbk.exercise.utils.ConstantUtil.LOG_START;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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

import com.tbk.exercise.dto.UserDto;
import com.tbk.exercise.restcontroller.UserControllerImpl;
import com.tbk.exercise.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserControllerImplTest {

	@InjectMocks
	private UserControllerImpl controller;

	@Mock
	private UserService service;

	@Test
	public void getListOfUsersTest() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		
		ResponseEntity<List<UserDto>> responseEntity;
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        
        when(service.getListOfUsers()).thenReturn(Arrays.asList(new UserDto(new UUID(124578, 0), LOG_START, LOG_START, LOG_END, null, null, null, LOG_START, false)) );
        responseEntity = controller.getListOfUsers();
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        
        when(service.getListOfUsers()).thenReturn(new ArrayList<>() );
        responseEntity = controller.getListOfUsers();
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
        
        log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
	}

}
