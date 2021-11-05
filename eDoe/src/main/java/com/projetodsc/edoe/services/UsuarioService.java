package com.projetodsc.edoe.services;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projetodsc.edoe.exception.NaoAutorizadoException;
import com.projetodsc.edoe.exception.UsuarioInvalidoException;
import com.projetodsc.edoe.exception.UsuarioNaoExisteException;
import com.projetodsc.edoe.model.TipoUsuario;
import com.projetodsc.edoe.model.Usuario;
import com.projetodsc.edoe.model.dto.LoginDTO;
import com.projetodsc.edoe.model.dto.UsuarioDTO;
import com.projetodsc.edoe.repository.UsuariosRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuariosRepository repositorio;
	
	@Autowired
	private JWTService jwtService;
	
	private Usuario usuarioLogado;
	
	public UsuarioService() {}
	
	public Usuario addUsuario(Usuario user) {
		return this.repositorio.save(user);
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
				"A operacao requerida nao pode ser realizada por este usuario: " + jwtService.getSujeitoDoToken(authHeader) + ".");
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
	
	public Usuario login(LoginDTO login) {	
		for (Usuario u : repositorio.findAll()) {
			if (login.getEmail().equals(u.getEmail()) && login.getSenha().equals(u.getSenha())) {
				usuarioLogado = u;
				return u;
			}
		}
		throw new UsuarioInvalidoException("Falha de login!", "Credenciais inválidas");	
	}
	
	public boolean validaLogin(LoginDTO loginDTO) {
		Optional<Usuario> user = repositorio.findByEmail(loginDTO.getEmail());
		if (user.isPresent() && user.get().getSenha().equals(loginDTO.getSenha()))
			return true;
		return false;
		
	}
	
	public boolean usuarioTemPermissao(String authHeader, String email) {
		String subject = jwtService.getSujeitoDoToken(authHeader);
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
