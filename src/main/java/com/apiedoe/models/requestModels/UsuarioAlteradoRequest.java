package com.apiedoe.models.requestModels;

import com.apiedoe.models.TipoUsuario;
import lombok.Data;

@Data
public class UsuarioAlteradoRequest {
	
	private String email;
	private TipoUsuario tipo;
	
	public UsuarioAlteradoRequest(String email, TipoUsuario tipo) {
		this.email = email;
		this.tipo = tipo;
	}

}
