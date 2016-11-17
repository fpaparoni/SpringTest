package com.javastaff.logintestapp.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javastaff.logintestapp.dao.UtenteRepository;
import com.javastaff.logintestapp.entity.UtenteEntity;
import com.javastaff.logintestapp.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	UtenteRepository utenteRepository;
	
	public boolean autentica(String username, String password) {
		boolean verifica=false;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] passwordDigest = md.digest(password.getBytes());
			String digest=new HexBinaryAdapter().marshal(passwordDigest);
			UtenteEntity utente=utenteRepository.findOne(username);
			if ((utente!=null) && (utente.getPassword().equals(digest)))
				verifica=true;
		}
		catch(NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		
		return verifica;
	}

}
