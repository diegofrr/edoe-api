package com.projetodsc.edoe.model.dto;

import com.projetodsc.edoe.exception.UsuarioInvalidoException;
import com.projetodsc.edoe.model.ClasseUsuario;
import com.projetodsc.edoe.model.TipoUsuario;
import com.projetodsc.edoe.model.Usuario;
import lombok.Data;

@Data
public class UsuarioDTO {

	private long id;
	private String nome;
	private String email;
	private String senha;
	private int celular;
	private ClasseUsuario classe;
	private int docIdentificacao;
	private TipoUsuario tipo;
	
	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(String nome, String email, String senha, int celular, ClasseUsuario classe, int doc) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.celular = celular;
		this.classe = classe;
		this.docIdentificacao = doc;
	}	

	public Usuario getUsuario() {
		return new Usuario(this.id, this.nome, this.email, this.senha, this.celular, this.classe, this.docIdentificacao);
	}
	
	public boolean validaUsuario() {
		if (nome == null || nome.isBlank() || nome.isEmpty())
			throw new UsuarioInvalidoException("Nome inválido!", "Confira o nome fornecido e tente novamente!");
		if (!email.contains("@gmail.com") && !email.contains("@dcx.ufpb.br") && !email.contains("@outlook.com") && !email.contains("@hotmail.com"))
			throw new UsuarioInvalidoException("E-mail inválido!", "Confira o e-mail fornecido e tente novamente!");
		if (senha == null || senha.isBlank()  || senha.isEmpty())
			throw new UsuarioInvalidoException("Senha inválida!", "Sua senha não pode ficar em branco");
		if(senha.length() < 6)
			throw new UsuarioInvalidoException("Senha muito curta", "Sua senha deve conter, no mínimo, 6 caracteres");
		if(senha.equals(nome))
			throw new UsuarioInvalidoException("Senha inválida!", "Sua senha não pode ser seu nome.");
			
		return true;
	}
	
	
	public UsuarioDTO(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
	
	public UsuarioDTO(String email, TipoUsuario tipo) {
		this.email = email;
		this.tipo = tipo;
	}
	

}