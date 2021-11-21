package com.apiedoe.models.requestModels;

import lombok.Data;

@Data
public class LoginReq {
	
	private String email;
	private String senha;
	
	public LoginReq(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

}
