package com.orders.security.service;




import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.orders.entities.Usuario;
import com.orders.services.UsuarioService;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	
	@Autowired
	private UsuarioService usuarioService;
	

	
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.getByMail(mail).orElseThrow(()-> new UsernameNotFoundException(mail));
		
		
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(true);
			session.setAttribute("usuariosession", usuario);

		return UsuarioPrincipal.build(usuario);
		
	}



    
}