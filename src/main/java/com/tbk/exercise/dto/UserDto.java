package com.tbk.exercise.dto;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserDto", description = "Data of users")
public class UserDto {
	
	@JsonProperty
	private UUID id;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private String email;
	
	@JsonProperty
	private String password;
	
	@JsonProperty
	private Date created;
	
	@JsonProperty
	private Date modified;
	
	@JsonProperty
	private Date lastLogin;
	
	@JsonProperty
	private String lastToken;
	
	@JsonProperty
	private boolean active;
	
}
