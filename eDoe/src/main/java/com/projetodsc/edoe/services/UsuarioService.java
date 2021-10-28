package com.projetodsc.edoe.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projetodsc.edoe.models.TipoUsuario;
import com.projetodsc.edoe.models.Usuario;
import com.projetodsc.edoe.models.dtos.UsuarioDTO;
import com.projetodsc.edoe.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repositorio;

	public Usuario criarDoador(UsuarioDTO usuarioDTO) {
		Usuario user = usuarioDTO.getUsuario();
		user.setTipo(TipoUsuario.DOADOR);
		return repositorio.save(user);
	}
	
	public Usuario criarReceptor(UsuarioDTO usuarioDTO) {
		Usuario user = usuarioDTO.getUsuario();
		user.setTipo(TipoUsuario.RECEPTOR);
		return repositorio.save(user);
	}
	
	public Usuario criarDoadorReceptor(UsuarioDTO usuarioDTO) {
		Usuario user = usuarioDTO.getUsuario();
		user.setTipo(TipoUsuario.DOADOR_RECEPTOR);
		return repositorio.save(user);	
	}
	
	public Usuario criarAdmin(UsuarioDTO usuarioDTO) {
		Usuario user = usuarioDTO.getUsuario();
		user.setTipo(TipoUsuario.ADMIN);
		return repositorio.save(user);
	}
	
	public Usuario adicionarUsuario(UsuarioDTO user) {
		return repositorio.save(user.getUsuario());
	}

	public List<Usuario> listaUsuarios() {
		return repositorio.findAll();

	}
	
	public Usuario validaUsuario(Usuario usuario) {
		Usuario user = repositorio.findByEmail(usuario.getEmail());
		return user;
	}
	
	
}
