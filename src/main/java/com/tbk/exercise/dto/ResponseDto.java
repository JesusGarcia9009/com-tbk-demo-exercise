package com.tbk.exercise.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@ApiModel(value = "Response", description = "Authenticated user data")
public class ResponseDto {
	
	@JsonProperty
	@ApiModelProperty(value = "Token of the user to be authenticated", name = "email", example = "askjdakjsdh78934579jzasfcjksdf098we458934")
	private String token;
	
	@JsonProperty
	@ApiModelProperty(value = "registered user identifier", name = "id", example = "ajskaskajskaksa")
	private String id;

	@JsonProperty
	@ApiModelProperty(value = "user creation date", name = "created", example = "02-02-2022 02:02:02")
	private Date created;

	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@ApiModelProperty(value = "user last modified date", name = "modified", example = "02-02-2022 02:02:02")
	private Date modified;
	
	@JsonProperty("last_login")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@ApiModelProperty(value = "date of last user authentication", name = "lastLogin", example = "02-02-2022 02:02:02")
	private Date lastLogin;
	
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@ApiModelProperty(value = "It is active", name = "active", example = "true / false")
	private boolean active;
	
}
