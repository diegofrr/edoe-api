package com.projetodsc.edoe.models.dtos;

import lombok.Data;

@Data
public class LoginDTO {
	
	private String email;
	private String senha;
	
	public LoginDTO() {}
	
	public LoginDTO(String email, String senha){
		this.email = email;
		this.senha = senha;
	}

}
