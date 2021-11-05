package com.projetodsc.edoe.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projetodsc.edoe.exception.NaoAutenticadoException;
import com.projetodsc.edoe.filter.FiltersToken;
import com.projetodsc.edoe.model.dto.UsuarioDTO;

@Service
public class JWTService {

	@Autowired
	private UsuarioService usuarioService;
	public static final String TOKEN_KEY = "awdsfoas";

	public LoginResponse autentica(UsuarioDTO usuario) {
		if (!usuarioService.validaLogin(usuario))
			throw new NaoAutenticadoException("Usuário ou senha inválidos", "");

		String token = geraToken(usuario.getEmail());
		return new LoginResponse(token);
	}

	private String geraToken(String email) {
		return Jwts.builder().setHeaderParam("typ", "JWT").setSubject(email)
				.signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 1000)).compact();
	}

	public String getTokenSubject(String authorizationHeader) {
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
			throw new SecurityException("Token inexistente ou mal formatado!");

		String token = authorizationHeader.substring(FiltersToken.TOKEN_INDEX);
		String subject = null;
		try {
			subject = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
		} catch (SecurityException e) {
			throw new SecurityException("Token inválido ou expirado!");
		}
		return subject;
	}

}
