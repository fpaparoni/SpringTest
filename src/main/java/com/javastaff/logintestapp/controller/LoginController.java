package com.javastaff.logintestapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javastaff.logintestapp.form.LoginForm;
import com.javastaff.logintestapp.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String mostraLogin(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String effettuaLogin(@Valid @ModelAttribute LoginForm loginForm,BindingResult result,Model model) {
		if (result.hasErrors()) 
			return "login";
		else {
			boolean autenticazioneEffettuata=loginService.autentica(loginForm.getUsername(),loginForm.getPassword());
			if (autenticazioneEffettuata)
				return "loginEffettuato";
			else {
				model.addAttribute("errorMessage", "Autenticazione non riuscita");
				return "login";
			}	
		}
	}
	
	
}
