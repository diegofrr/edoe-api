package com.projetodsc.edoe.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "usuario")
public class Usuario {
	
	@Column(nullable = false, length = 50)
	private String nome;
	
	@Id
	@Column(nullable = false, length = 30)
	private String email;
	
	@Column(nullable = false, length = 11)
	private int celular;
	
	@Column(nullable = false, length = 10)
	private String classe;
	
	@Column(nullable = false, length = 11)
	private int cpf;

}
