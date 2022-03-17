package com.tbk.exercise.utils;

/**
 * ConstantUtil  - Class used for system constants
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public class ConstantUtil {
	
	private ConstantUtil () {
		
	}
	
	/**
	 * Logs letter
	 */
	public static final String LOG_START = "[INFO :: %s] - START.";
	public static final String LOG_END = "[INFO :: %s] - END.";

	public static final String AUTHORITY_ADMIN = "Admin";
	
	public static final String AUTHORITIES = "Authorities";
	public static final String USERNAME_MAIL = "UserNameOrMail";
	
	public static final String AUTHORIZATION = "Authorization";
	public static final String BEARER = "Bearer ";
	
	/**
	 * exceptions
	 */
	public static final String MAIL_FOUND = "El correo ya registrado.";
	
	
}
