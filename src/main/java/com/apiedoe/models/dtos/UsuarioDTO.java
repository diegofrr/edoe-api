package com.apiedoe.models.dtos;

import com.apiedoe.exceptions.UsuarioInvalidoException;
import com.apiedoe.models.ClasseUsuario;
import com.apiedoe.models.TipoUsuario;
import com.apiedoe.models.Usuario;
import com.apiedoe.models.requestModels.UsuarioReq;

import lombok.Data;

@Data
public class UsuarioDTO {

	private String nome;
	private String email;
	private String senha;
	private int celular;
	private ClasseUsuario classe;
	private int docIdentificacao;
	private TipoUsuario tipo = TipoUsuario.DOADOR;

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
	
	public UsuarioDTO(UsuarioReq user) {
		this.nome = user.getNome();
		this.email = user.getEmail();
		this.senha = user.getSenha();
		this.celular = user.getCelular();
		this.classe = user.getClasse();
		this.docIdentificacao = user.getDocIdentificacao();
	}

	public Usuario getUsuario() {
		return new Usuario(this.nome, this.email, this.senha, this.celular, this.classe, this.docIdentificacao,
				this.tipo);
	}

	public boolean validaUsuario() {
		if (nome == null || nome.isBlank() || nome.isEmpty())
			throw new UsuarioInvalidoException("Nome inválido!", "Confira o nome fornecido e tente novamente!");
		if (!email.contains("@GMAIL.COM") && !email.contains("@DCX.UFPB.BR") && !email.contains("@OUTLOOK.COM")
				&& !email.contains("@HOTMAIL.COM"))
			throw new UsuarioInvalidoException("E-mail inválido!", "Confira o e-mail fornecido e tente novamente!");
		if (senha == null || senha.isBlank() || senha.isEmpty())
			throw new UsuarioInvalidoException("Senha inválida!", "Sua senha não pode ficar em branco");
		if (senha.length() < 6)
			throw new UsuarioInvalidoException("Senha muito curta", "Sua senha deve conter, no mínimo, 6 caracteres");
		if (senha.equals(nome))
			throw new UsuarioInvalidoException("Senha inválida!", "Sua senha não pode ser seu nome.");
		return true;
	}

}
