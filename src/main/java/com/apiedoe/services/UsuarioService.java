package com.apiedoe.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apiedoe.exceptions.NaoAutenticadoException;
import com.apiedoe.exceptions.NaoAutorizadoException;
import com.apiedoe.exceptions.UsuarioInvalidoException;
import com.apiedoe.exceptions.UsuarioNaoExisteException;
import com.apiedoe.models.TipoUsuario;
import com.apiedoe.models.Usuario;
import com.apiedoe.models.dtos.UsuarioDTO;
import com.apiedoe.models.requestModels.LoginRequest;
import com.apiedoe.models.requestModels.UsuarioAlteradoRequest;
import com.apiedoe.models.requestModels.UsuarioCadastro;
import com.apiedoe.models.responseModels.UsuarioResponse;
import com.apiedoe.repositories.UsuariosRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuariosRepository repositorio;

	@Autowired
	private JWTService jwtService;

	public UsuarioService() {
	}

	public Usuario addUsuario(Usuario user) {
		return this.repositorio.save(user);
	}

	public UsuarioResponse alteraTipo(UsuarioAlteradoRequest dados, String authHeader) {
		String email = dados.getEmail().toUpperCase();
		TipoUsuario novoTipo = dados.getTipo();
		String subject = jwtService.getSujeitoDoToken(authHeader);
		Optional<Usuario> usuarioDoToken = repositorio.findByEmailIgnoreCase(subject);

		if (usuarioDoToken.get().getTipo() != TipoUsuario.ADMIN)
			throw new NaoAutorizadoException("Sem permissão!", "Somente administradores");

		if (!repositorio.existsByEmailIgnoreCase(email))
			throw new UsuarioNaoExisteException("Usuário não encontrado!",
					"Este e-mail não corresponde à nenhum usuário!");

		if (repositorio.findByEmailIgnoreCase(email).get().getTipo() == TipoUsuario.ADMIN)
			throw new NaoAutorizadoException("Sem permissão", "Não é possível alterar dados de outros administradores");

		Optional<Usuario> user = repositorio.findByEmailIgnoreCase(email);
		user.get().setTipo(novoTipo);
		repositorio.save(user.get());
		return new UsuarioResponse(user.get());

	}

	public Usuario getUsuario(String email, String authHeader) {
		email = email.toUpperCase();
		Optional<Usuario> optUsuario = repositorio.findByEmailIgnoreCase(email);
		if (!optUsuario.isEmpty() && usuarioTemPermissao(authHeader, email)) {
			return optUsuario.get();
		}
		throw new NaoAutorizadoException("Usuario nao tem permissao",
				"A operacao requerida nao pode ser realizada por este usuario: "
						+ jwtService.getSujeitoDoToken(authHeader) + ".");
	}

	public UsuarioResponse adicionaUsuario(UsuarioCadastro usuario) {
		UsuarioDTO user = new UsuarioDTO(usuario);
		user.setNome(user.getNome().toUpperCase());
		user.setEmail(user.getEmail().toUpperCase());
		user.validaUsuario();

		if (user.getTipo() == TipoUsuario.ADMIN)
			throw new UsuarioInvalidoException("Cadastro inválido!",
					"Somente outros administradores do sistema podem atribuir o cargo de administrador para outro usuário.");

		if (repositorio.findAll().size() == 0)
			user.setTipo(TipoUsuario.ADMIN);

		else if (repositorio.existsByEmailIgnoreCase(user.getEmail()))
			throw new UsuarioInvalidoException("E-mail já cadastrado!",
					"Já existe um usuário cadastrado com o este e-mail!");
		

		repositorio.save(user.getUsuario());
		return new UsuarioResponse(user);
	}

	public List<UsuarioResponse> listarUsuarios() {
		List<UsuarioResponse> responseList = new ArrayList<>();
		for (Usuario user : repositorio.findAll()) {
			responseList.add(new UsuarioResponse(user));
		}
		return responseList;
	}

	public boolean validaLogin(LoginRequest login) {
		Optional<Usuario> user = repositorio.findByEmailIgnoreCase(login.getEmail());
		if (user.isPresent() && user.get().getSenha().equals(login.getSenha()))
			return true;
		return false;

	}

	public boolean usuarioTemPermissao(String authHeader, String email) {
		String subject = jwtService.getSujeitoDoToken(authHeader);
		Optional<Usuario> usuario = repositorio.findByEmailIgnoreCase(subject);
		return usuario.isPresent() && usuario.get().getEmail().equals(email);
	}

	public boolean usuarioEhAdmin(String authHeader) {
		String subject = jwtService.getSujeitoDoToken(authHeader);
		Optional<Usuario> usuario = repositorio.findByEmailIgnoreCase(subject);
		if (usuario.get().getTipo() != TipoUsuario.ADMIN)
			throw new NaoAutenticadoException("Sem permissão",
					"Esta funcionalidade é somente para administradores do sistema.");
		return true;
	}

	public Usuario removeUsuario(String email, String authHeader) {
		Usuario usuario = getUsuario(email, authHeader);
		if (usuarioTemPermissao(authHeader, email)) {
			repositorio.delete(usuario);
		}
		return usuario;
	}

}
