package com.javastaff.logintestapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javastaff.logintestapp.entity.UtenteEntity;

public interface UtenteRepository extends JpaRepository<UtenteEntity,String>,UtenteRepositoryCustom{

}
