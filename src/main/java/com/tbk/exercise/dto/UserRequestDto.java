package com.tbk.exercise.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
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
@ApiModel(value = "UserRequest", description = "Data to register a user")
public class UserRequestDto {
	
	@JsonProperty
	@NotEmpty(message = "not empty")
	@ApiModelProperty(value = "name of the user to register", name = "name", example = "Juan Morales")
	private String name;
	
	@Email(message = "not valid")
	@JsonProperty
	@NotEmpty(message = "not empty")
	@ApiModelProperty(value = "Email of the user to be authenticated", name = "email", example = "test@test.cl")
	private String email;
	
	@JsonProperty
	@NotEmpty(message = "not empty")
	@ApiModelProperty(value = "Password of the user to be authenticated", name = "password", example = "juan.12")
	private String password;
	
	@Valid
	@ApiModelProperty(value = "List of phone numbers of a user", name = "phones")
	private List<PhoneRequestDto> phones = new ArrayList<>();
	
}
