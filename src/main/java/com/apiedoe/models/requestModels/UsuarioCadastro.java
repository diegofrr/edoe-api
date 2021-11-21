package com.apiedoe.models.requestModels;

import com.apiedoe.models.ClasseUsuario;
import com.apiedoe.models.TipoUsuario;

import lombok.Data;

@Data
public class UsuarioCadastro {

	private String nome;
	private String email;
	private String senha;
	private int celular;
	private ClasseUsuario classe;
	private int docIdentificacao;
	private TipoUsuario tipo;
	
	public UsuarioCadastro() {}

	public UsuarioCadastro(String nome, String email, String senha, int celular, ClasseUsuario classe,
			int docIdentificacao, TipoUsuario tipo) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.celular = celular;
		this.classe = classe;
		this.docIdentificacao = docIdentificacao;
		this.tipo = tipo;
	}

}
