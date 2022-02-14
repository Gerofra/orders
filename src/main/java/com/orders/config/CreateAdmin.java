package com.orders.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.orders.entities.Rol;
import com.orders.entities.Usuario;
import com.orders.enums.RolEnum;
import com.orders.repos.UsuarioRepo;
import com.orders.services.RolService;
import com.orders.services.UsuarioService;

@Service
public class CreateAdmin implements CommandLineRunner {

	@Autowired
	UsuarioService usuarioService;
	@Autowired
	UsuarioRepo usuarioRepo;

	@Autowired
	RolService rolService;

	@Override
	public void run(String... args) throws Exception {
				
		
		// ASIGNO ROL ADINISTRADOR	
		/*
		Usuario usuario = new Usuario();
		String encriptada = new BCryptPasswordEncoder().encode("admin");
		
		usuario.setName("Gerónimo");
		usuario.setLastname("Pericoli");
		usuario.setMail("geronimopericoli@gmail.com");
		usuario.setPassword(encriptada);
        usuario.setArea("Administrador");
		
		Rol rolAdmin = rolService.getByRolEnum(RolEnum.ROLE_ADMIN).get();
		Rol rolUser = rolService.getByRolEnum(RolEnum.ROLE_USER).get();
		
		Set<Rol> roles = new HashSet<>();
		roles.add(rolUser);
		roles.add(rolAdmin);
		usuario.setRoles(roles);
		
		usuarioRepo.save(usuario);
		*/
		
		/*
		Usuario usuario = new Usuario();
		String encriptada = new BCryptPasswordEncoder().encode("admin");
		
		usuario.setName("Administrador");
		usuario.setLastname("");
		usuario.setMail("admin@gmail.com");
		usuario.setPassword(encriptada);
		
		Rol rolMod = rolService.getByRolEnum(RolEnum.ROLE_MOD).get();
		Rol rolUser = rolService.getByRolEnum(RolEnum.ROLE_USER).get();
		
		Set<Rol> roles = new HashSet<>();
		roles.add(rolUser);
		roles.add(rolMod);
		usuario.setRoles(roles);
		
		usuarioRepo.save(usuario);
		*/
	}
}
