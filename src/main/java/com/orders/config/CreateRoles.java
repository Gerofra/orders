package com.orders.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.orders.entities.Rol;
import com.orders.enums.RolEnum;
import com.orders.services.RolService;

@Service
public class CreateRoles implements CommandLineRunner {

	@Autowired
	RolService rolService;

	@Override
	public void run(String... args) throws Exception {
		
		// AGREGAN LOS ROLES A LA BASE DE DATOS A TRAVES DE LINEA DE COMANDO
//		Rol rolAdmin = new Rol(RolEnum.ROLE_ADMIN);
//		Rol rolUser = new Rol(RolEnum.ROLE_USER);
//		
//		rolService.save(rolAdmin);
//		rolService.save(rolUser);
//		
//		
		/*
		Rol rolMod = new Rol(RolEnum.ROLE_MOD);
		rolService.save(rolMod);
		*/
	}
}
