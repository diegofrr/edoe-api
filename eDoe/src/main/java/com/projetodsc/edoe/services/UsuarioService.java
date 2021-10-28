package com.projetodsc.edoe.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projetodsc.edoe.models.Usuario;
import com.projetodsc.edoe.models.dtos.UsuarioDTO;
import com.projetodsc.edoe.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repositorio;

	public Usuario adicionarUsuario(UsuarioDTO user) {
		return repositorio.save(user.getUsuario());
	}

	public List<Usuario> listaUsuarios() {
		return repositorio.findAll();

	}
}
