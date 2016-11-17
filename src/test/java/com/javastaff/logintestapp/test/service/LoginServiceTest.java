package com.javastaff.logintestapp.test.service;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javastaff.logintestapp.dao.UtenteRepository;
import com.javastaff.logintestapp.entity.UtenteEntity;
import com.javastaff.logintestapp.service.LoginService;

/**
 * Casi di test relativi a LoginService
 * */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ServiceConfigConfiguration.class})
public class LoginServiceTest {
	
	protected static Logger logger = Logger.getLogger(LoginServiceTest.class);
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	UtenteRepository utenteRepository;
	
	UtenteEntity utente;
	
	@Before
    public void setup() throws ParseException {
    	logger.info("Caricamento dati");
    	Date dataRegistrazione=new SimpleDateFormat("dd/MM/yyyy").parse("16/11/2016");
		utente=new UtenteEntity();
		utente.setUsername("utente");
		utente.setPassword("5F4DCC3B5AA765D61D8327DEB882CF99");
		utente.setDataRegistrazione(dataRegistrazione);
		Mockito.when(utenteRepository.findOne("utente")).thenReturn(utente);
    }
	
	@Test
	public void testAutenticazione(){
		logger.info("testAutenticazione");
		boolean autenticazione=loginService.autentica(utente.getUsername(), "password");
		assertEquals(true,autenticazione);
	}
	
	@Test
	public void testAutenticazioneFallita(){
		logger.info("testAutenticazioneFallita");
		boolean autenticazione=loginService.autentica(utente.getUsername(), "CIAO!");
		assertEquals(false,autenticazione);
	}
}
