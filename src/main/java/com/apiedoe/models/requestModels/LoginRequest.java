package com.apiedoe.models.requestModels;

import lombok.Data;

@Data
public class LoginRequest {
	
	private String email;
	private String senha;
	
	public LoginRequest(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

}
