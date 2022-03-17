package com.tbk.exercise.dto;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "LogInRequest", description = "Data to authenticate a user")
public class LogInRequestDto {
	
	@JsonProperty
	@NotEmpty(message = "test de datos vacios")
	@ApiModelProperty(value = "Email of the user to be authenticated", name = "email", example = "test@test.cl")
	private String email;
	
	@JsonProperty
	@ApiModelProperty(value = "Password of the user to be authenticated", name = "password", example = "juan.12")
	private String password;
	
}
