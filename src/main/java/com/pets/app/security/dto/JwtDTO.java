package com.pets.app.security.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtDTO {

	private String token;
	private String bearer = "Bearer";
	private String nombreUsuario;
	
	//Collections: De authorities
	private Collection<? extends GrantedAuthority> authorities;

	public JwtDTO(String token, String nombreUsuario, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.token = token;
		this.nombreUsuario = nombreUsuario;
		this.authorities = authorities;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getBearer() {
		return bearer;
	}

	public void setBearer(String bearer) {
		this.bearer = bearer;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	
}
