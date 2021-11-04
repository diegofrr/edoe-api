package com.projetodsc.edoe.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetodsc.edoe.exception.UsuarioJaExisteException;
import com.projetodsc.edoe.model.TipoUsuario;
import com.projetodsc.edoe.model.Usuario;
import com.projetodsc.edoe.model.dto.UsuarioDTO;
import com.projetodsc.edoe.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repositorio;
	
	private Usuario usuarioLogado;
	
	public void alteraFuncao(Usuario usuario, TipoUsuario novoTipo) {
		if (usuarioLogado.getTipo() == TipoUsuario.ADMIN)
			usuario.setTipo(novoTipo);
	}

//	public Usuario criarDoador(UsuarioDTO usuarioDTO) {
//		Usuario user = repositorio.findByEmail(usuarioDTO.getEmail());
//		if (user == null) {
//			usuarioDTO.setTipo(TipoUsuario.DOADOR);
//			return repositorio.save(usuarioDTO.getUsuario());
//		} else {
//			return user;
//		}
//	}
//	
//	public Usuario criarReceptor(UsuarioDTO usuarioDTO) {
//		Usuario user = repositorio.findByEmail(usuarioDTO.getEmail());
//		if (user == null) {
//			usuarioDTO.setTipo(TipoUsuario.RECEPTOR);
//			return repositorio.save(usuarioDTO.getUsuario());
//		} else {
//			return user;
//		}
//	}
	
//	public Usuario criarDoadorReceptor(UsuarioDTO usuarioDTO) {
//		Usuario user = repositorio.findByEmail(usuarioDTO.getEmail());
//		if (user == null) {
//			usuarioDTO.setTipo(TipoUsuario.DOADOR_RECEPTOR);
//			return repositorio.save(usuarioDTO.getUsuario());
//		} else {
//			return user;
//		}
//		
//	}
	
//	public Usuario criarAdmin(UsuarioDTO usuarioDTO) {
//		Usuario user = repositorio.findByEmail(usuarioDTO.getEmail());
//		if (user == null) {
//			usuarioDTO.setTipo(TipoUsuario.ADMIN);
//			return repositorio.save(usuarioDTO.getUsuario());
//		} else {
//			return user;
//		}
//	}
	
	public Usuario adicionaUsuario(UsuarioDTO user) {
		
		user.validaUsuario(user);
		if (!repositorio.findByEmail(user.getEmail()).isEmpty()) {
			throw new UsuarioJaExisteException("E-mail já cadastrado no sistema", "Já existe um usuário cadastrado com este e-mail.");
		}
		return repositorio.save(user.getUsuario());
	}

	public List<Usuario> listaUsuarios() {
		return repositorio.findAll();

	}
	
	
	
}
