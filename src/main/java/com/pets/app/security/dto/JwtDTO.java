package com.pets.app.security.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtDTO {

	private String token;

	public JwtDTO() {
	}

	public JwtDTO(String token) {
		super();
		this.token = token;

	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
}
