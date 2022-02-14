package com.orders.security.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.orders.entities.Usuario;



public class UsuarioPrincipal implements UserDetails {

	
	private String id;
	private String password;
	private String mail;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	
	
	

	
	
	
	public UsuarioPrincipal(String id, String password, String mail,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.password = password;
		this.mail = mail;
		this.authorities = authorities;
	}


	public static UsuarioPrincipal build(Usuario usuario) {
		List<GrantedAuthority> authorities = 
				usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolEnum().name())).collect(Collectors.toList());
		
		
		
		return new UsuarioPrincipal(usuario.getId(), usuario.getPassword(), usuario.getMail(), authorities);
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return mail;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	

	public String getId() {
		return id;
	}



}
