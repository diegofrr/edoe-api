package com.projetodsc.edoe.services;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

	public Usuario criarDoador(UsuarioDTO usuarioDTO) {
		Usuario user = repositorio.findByEmail(usuarioDTO.getEmail());
		if (user == null) {
			usuarioDTO.setTipo(TipoUsuario.DOADOR);
			return repositorio.save(usuarioDTO.getUsuario());
		} else {
			return user;
		}
	}
	
	public Usuario criarReceptor(UsuarioDTO usuarioDTO) {
		Usuario user = repositorio.findByEmail(usuarioDTO.getEmail());
		if (user == null) {
			usuarioDTO.setTipo(TipoUsuario.RECEPTOR);
			return repositorio.save(usuarioDTO.getUsuario());
		} else {
			return user;
		}
	}
	
	public Usuario criarDoadorReceptor(UsuarioDTO usuarioDTO) {
		Usuario user = repositorio.findByEmail(usuarioDTO.getEmail());
		if (user == null) {
			usuarioDTO.setTipo(TipoUsuario.DOADOR_RECEPTOR);
			return repositorio.save(usuarioDTO.getUsuario());
		} else {
			return user;
		}
		
	}
	
	public Usuario criarAdmin(UsuarioDTO usuarioDTO) {
		Usuario user = repositorio.findByEmail(usuarioDTO.getEmail());
		if (user == null) {
			usuarioDTO.setTipo(TipoUsuario.ADMIN);
			return repositorio.save(usuarioDTO.getUsuario());
		} else {
			return user;
		}
	}
	
	public Usuario adicionarUsuario(UsuarioDTO user) {
		Usuario usuario = user.getUsuario();
		if (repositorio.findAll().size() == 0) usuario.setTipo(TipoUsuario.ADMIN);// caso não haja nenhum usuário no sistema, o primeiro cadastrado será o admin
		return repositorio.save(usuario);
	}

	public List<Usuario> listaUsuarios() {
		return repositorio.findAll();

	}
	
	public Usuario validaUsuario(Usuario usuario) {
		Usuario user = repositorio.findByEmail(usuario.getEmail());
		return user;
	}
	
	public Usuario logar(UsuarioDTO user) {
		Usuario usuario = repositorio.findByEmail(user.getEmail());
		if (usuario.getSenha().equals(user.getSenha())) {
			usuarioLogado = usuario;
			return usuario;
		} return null;
	}
	
	
}
