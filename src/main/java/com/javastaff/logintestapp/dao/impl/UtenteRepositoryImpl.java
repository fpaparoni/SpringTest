package com.javastaff.logintestapp.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.javastaff.logintestapp.dao.UtenteRepositoryCustom;
import com.javastaff.logintestapp.entity.UtenteEntity;

public class UtenteRepositoryImpl implements UtenteRepositoryCustom{
	
	@PersistenceContext
	private EntityManager em;
	
	public List<UtenteEntity> listaUtentiRangeDataRegistrazione(Date dataRegistrazioneInizio,Date dataRegistrazioneFine) {
		List<UtenteEntity> utenteList=em.createQuery("select u from UtenteEntity u where "
				+ "dataRegistrazione BETWEEN :dataRegistrazioneInizio AND :dataRegistrazioneFine",UtenteEntity.class)
		  .setParameter("dataRegistrazioneInizio", dataRegistrazioneInizio)
		  .setParameter("dataRegistrazioneFine", dataRegistrazioneFine).getResultList();
		return utenteList;
	}

}
