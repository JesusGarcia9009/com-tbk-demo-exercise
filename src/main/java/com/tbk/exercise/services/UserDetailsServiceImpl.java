package com.tbk.exercise.services;

import static com.tbk.exercise.utils.ConstantUtil.LOG_END;
import static com.tbk.exercise.utils.ConstantUtil.LOG_START;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tbk.exercise.dto.UserLogIn;
import com.tbk.exercise.utils.HelperUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		UserLogIn userDetails = (UserLogIn) new UserLogIn(userService.getUserByEmail(email), HelperUtil.getAuthList());
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return userDetails;
	}
	
}
