package com.javastaff.logintestapp.test.service;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import com.javastaff.logintestapp.dao.UtenteRepository;
import com.javastaff.logintestapp.service.LoginService;
import com.javastaff.logintestapp.service.impl.LoginServiceImpl;

/**
 * Classe di configurazione usata per i test relativi allo strato Service
 * */

@ImportResource("spring/applicationContext.xml")
public class ServiceConfigConfiguration {
	@Bean
    public LoginService utenteService() {
        return new LoginServiceImpl();
    }
	
    @Bean
    public UtenteRepository utenteRepository() {
        return Mockito.mock(UtenteRepository.class);
    }
}
