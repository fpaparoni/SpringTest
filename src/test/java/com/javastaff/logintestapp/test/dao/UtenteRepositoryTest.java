package com.javastaff.logintestapp.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.javastaff.logintestapp.dao.UtenteRepository;
import com.javastaff.logintestapp.entity.UtenteEntity;


/**
 * Casi di test relativi a UtenteRepository
 * */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DaoConfigConfiguration.class, TestDataContextConfiguration.class })
@Transactional
public class UtenteRepositoryTest {
	protected static Logger logger = Logger.getLogger(UtenteRepositoryTest.class);
	
	@Autowired
	UtenteRepository utenteRepository;
	
	UtenteEntity utente;
	Date dataRegistrazione1;
	Date dataRegistrazione2;
	Date dataRegistrazioneUtente;
	
	@Before
	public void setupData() throws ParseException {
		logger.info("Caricamento dati");
		dataRegistrazioneUtente=new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2009");
		dataRegistrazione1=new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2009");
		dataRegistrazione2=new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2010");
		utente=new UtenteEntity();
		utente.setUsername("test");
		utente.setPassword("");
		utente.setDataRegistrazione(dataRegistrazioneUtente);
		utenteRepository.save(utente);
	}

	@Test
	public void testListaUtentiRangeDataRegistrazione() {
		logger.info("testListaUtentiRangeDataRegistrazione()");
		List<UtenteEntity> listaUtenti=utenteRepository.listaUtentiRangeDataRegistrazione(dataRegistrazione1, dataRegistrazione2);
		assertNotNull(listaUtenti);
		assertEquals(listaUtenti.size(), 1);
		assertEquals(listaUtenti.get(0), utente);
	}
	
	@After
	public void cleanData() {
		utenteRepository.delete(utente);
	}
}
