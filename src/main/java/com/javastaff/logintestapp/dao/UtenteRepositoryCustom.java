package com.javastaff.logintestapp.dao;

import java.util.Date;
import java.util.List;

import com.javastaff.logintestapp.entity.UtenteEntity;

public interface UtenteRepositoryCustom {
	List<UtenteEntity> listaUtentiRangeDataRegistrazione(Date dataRegistrazioneInizio,Date dataRegistrazioneFine);
}
