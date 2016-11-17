package com.javastaff.logintestapp.test.controller;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import com.javastaff.logintestapp.controller.LoginController;
import com.javastaff.logintestapp.service.LoginService;

/**
 * Classe di configurazione usata per i test relativi allo strato Controller
 * */

@ImportResource({"spring/applicationContext.xml","spring/spring-mvc-servlet.xml"})
public class ControllerConfigConfiguration {
	@Bean
	public LoginService loginService() {
		return Mockito.mock(LoginService.class);
	}
	 
	@Bean
	public LoginController loginController() {
		return new LoginController();
	}
}

