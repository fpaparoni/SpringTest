package com.javastaff.logintestapp.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.javastaff.logintestapp.controller.LoginController;
import com.javastaff.logintestapp.entity.UtenteEntity;
import com.javastaff.logintestapp.service.LoginService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ControllerConfigConfiguration.class})
public class LoginControllerTest {
	protected static Logger logger = Logger.getLogger(LoginControllerTest.class);
	
	@Autowired 
	LoginService loginService;
	
	@Autowired
	LoginController loginController;
	
	UtenteEntity utente;
	String password="password";
	String passwordSbagliata="sbagliata";
	
	@Before
    public void setup() throws Exception{
		Date dataRegistrazione=new SimpleDateFormat("dd/MM/yyyy").parse("16/11/2016");
		utente=new UtenteEntity();
		utente.setUsername("utente");
		utente.setPassword("5F4DCC3B5AA765D61D8327DEB882CF99");
		utente.setDataRegistrazione(dataRegistrazione);
		Mockito.when(this.loginService.autentica(utente.getUsername(), password)).thenReturn(true);
		Mockito.when(this.loginService.autentica(utente.getUsername(), passwordSbagliata)).thenReturn(false);
	}
	
	@Test
	public void testEffettuaLogin() throws Exception {
		logger.info("testEffettuaLogin()");
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.loginController).build();
		mockMvc.perform(post("/login/")
				.param("username", utente.getUsername())
				.param("password", password))
				.andExpect(status().isOk())
				.andExpect(view().name("loginEffettuato"));
	}
	
	@Test
	public void testEffettuaLoginErrata() throws Exception {
		logger.info("testEffettuaLoginErrata()");
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.loginController).build();
		mockMvc.perform(post("/login/")
				.param("username", utente.getUsername())
				.param("password", passwordSbagliata))
				.andExpect(model().attributeExists("errorMessage"))
				.andExpect(status().isOk())
				.andExpect(view().name("login"));
	}
	
	@Test
	public void testMostraLogin() throws Exception {
		logger.info("testMostraLogin()");
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.loginController).build();
		mockMvc.perform(get("/login/"))
				.andExpect(status().isOk())
				.andExpect(view().name("login"));
	}
}
