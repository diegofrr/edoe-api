package com.projetodsc.edoe.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetodsc.edoe.exception.NaoAutenticadoException;
import com.projetodsc.edoe.exception.UsuarioInvalidoException;
import com.projetodsc.edoe.exception.UsuarioNaoExisteException;
import com.projetodsc.edoe.model.TipoUsuario;
import com.projetodsc.edoe.model.Usuario;
import com.projetodsc.edoe.model.dto.UsuarioDTO;
import com.projetodsc.edoe.repository.UsuarioDAO;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDAO repositorio;
	
	private Usuario usuarioLogado;
	
	public UsuarioService() {}
	
	public String alteraFuncao(UsuarioDTO usuarioDTO) {
		String email = usuarioDTO.getEmail();
		TipoUsuario novoTipo = usuarioDTO.getTipo();
		
		if (usuarioLogado == null)
			throw new UsuarioInvalidoException("Não logado!", "Faça login no sistema!");
		
		if (usuarioLogado.getTipo() != TipoUsuario.ADMIN)
			throw new NaoAutenticadoException("Sem autorização", "Este espaço é somente para administradores do sistema.");
		
		if (!repositorio.existsByEmail(email))
			throw new UsuarioNaoExisteException("Usuário não encontrado!", "Este e-mail não corresponde à nenhum usuário!");
		
		if (repositorio.findByEmail(usuarioDTO.getEmail()).get().getTipo() == TipoUsuario.ADMIN)
			throw new UsuarioInvalidoException("Sem permissão", "Não é possível alterar dados de outros administradores");
		
		Optional<Usuario> user = repositorio.findByEmail(email);
		user.get().setTipo(novoTipo);
		repositorio.save(user.get());
		return "Função do usuário " + user.get().getNome() + " alterada para " + novoTipo;		
			
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
		
		user.validaUsuario();
		if (repositorio.existsByEmail(user.getEmail()))
			throw new UsuarioInvalidoException("E-mail já cadastrado!", "Já existe um usuário cadastrado com o este e-mail!");

		return repositorio.save(user.getUsuario());
	}

	public List<Usuario> listaUsuarios() {
		return repositorio.findAll();
	}
	
	public Usuario login(UsuarioDTO usuario) {	
		for (Usuario u : repositorio.findAll()) {
			if (usuario.getEmail().equals(u.getEmail()) && usuario.getSenha().equals(u.getSenha())) {
				usuarioLogado = u;
				return u;
			}
		}
		throw new UsuarioInvalidoException("Falha de login!", "Credenciais inválidas");	
	}
	
	
	
}
