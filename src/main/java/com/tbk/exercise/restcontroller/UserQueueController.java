package com.tbk.exercise.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import com.tbk.exercise.queue.Ticket;


/**
 * UserQueueController - Queue Services - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11.0.7
 */
public interface UserQueueController {

	public String home(Model model);
	
	public String submit(Ticket ticket);
	
	public String metrics();
	
	public ResponseEntity<HttpStatus> health();
	
	
}
