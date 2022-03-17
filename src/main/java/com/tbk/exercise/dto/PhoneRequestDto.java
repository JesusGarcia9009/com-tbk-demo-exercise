package com.tbk.exercise.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "PhoneRequestDto", description = "Phone numbers of a user")
public class PhoneRequestDto {

	@ApiModelProperty(value = "Phone number", name = "name", example = "12457889")
	private String number;
	
	@ApiModelProperty(value = "City code", name = "citycode", example = "9")
	private String citycode;
	
	@ApiModelProperty(value = "Contry code", name = "contrycode", example = "+56")
	private String contrycode;
	
}
