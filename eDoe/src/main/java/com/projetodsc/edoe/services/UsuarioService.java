package com.projetodsc.edoe.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetodsc.edoe.exception.NaoAutenticadoException;
import com.projetodsc.edoe.exception.NaoAutorizadoException;
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
	
	@Autowired
	private JWTService jwtService;
	
	private Usuario usuarioLogado;
	
	public UsuarioService() {}
	
	public Usuario addUsuario(Usuario user) {
		return repositorio.save(user);
	}
	
	public String alteraFuncao(UsuarioDTO usuarioDTO) {
		String email = usuarioDTO.getEmail();
		TipoUsuario novoTipo = usuarioDTO.getTipo();
		
		if (usuarioLogado == null)
			throw new UsuarioInvalidoException("Não logado!", "Faça login no sistema!");
		
		if (usuarioLogado.getTipo() != TipoUsuario.ADMIN)
			throw new NaoAutorizadoException("Sem autorização", "Este espaço é somente para administradores do sistema.");
		
		if (!repositorio.existsByEmail(email))
			throw new UsuarioNaoExisteException("Usuário não encontrado!", "Este e-mail não corresponde à nenhum usuário!");
		
		if (repositorio.findByEmail(usuarioDTO.getEmail()).get().getTipo() == TipoUsuario.ADMIN)
			throw new NaoAutorizadoException("Sem permissão", "Não é possível alterar dados de outros administradores");
		
		Optional<Usuario> user = repositorio.findByEmail(email);
		user.get().setTipo(novoTipo);
		repositorio.save(user.get());
		return "Função do usuário " + user.get().getNome() + " alterada para " + novoTipo;		
			
	}

	public Usuario getUsuario(String email, String authHeader) {
		Optional<Usuario> optUsuario = repositorio.findByEmail(email);
		if (!optUsuario.isEmpty() && usuarioTemPermissao(authHeader, email)) {
			return optUsuario.get();
		}
		throw new NaoAutorizadoException("Usuario nao tem permissao",
				"A operacao requerida nao pode ser realizada por este usuario: " + jwtService.getTokenSubject(authHeader) + ".");
	}
	
	
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
	
	public boolean validaLogin(UsuarioDTO usuario) {
		Optional<Usuario> user = repositorio.findByEmail(usuario.getEmail());
		if (user.isPresent() && user.get().getSenha().equals(usuario.getSenha()))
			return true;
		return false;
		
	}
	
	public boolean usuarioTemPermissao(String authHeader, String email) {
		String subject = jwtService.getTokenSubject(authHeader);
		Optional<Usuario> usuario = repositorio.findByEmail(subject);
		return usuario.isPresent() && usuario.get().getEmail().equals(email);
	}
	
	public Usuario removeUsuario(String email, String authHeader) {
		Usuario usuario = getUsuario(email, authHeader);
		if (usuarioTemPermissao(authHeader, email)) {
			repositorio.delete(usuario);
		}
		return usuario;
	}
	
	
	
}
