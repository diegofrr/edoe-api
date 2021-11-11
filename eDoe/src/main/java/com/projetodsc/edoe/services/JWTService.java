package com.projetodsc.edoe.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projetodsc.edoe.exceptions.NaoAutenticadoException;
import com.projetodsc.edoe.filters.FiltersToken;
import com.projetodsc.edoe.models.dtos.LoginDTO;
import com.projetodsc.edoe.models.dtos.LoginResponse;

@Service
public class JWTService {

	@Autowired
	private UsuarioService usuarioService;
	public static final String TOKEN_KEY = "wdsjfhkwbfdgwuierhweij";
	
	public LoginResponse autentica(LoginDTO loginDTO) {
		loginDTO.setEmail(loginDTO.getEmail().toUpperCase());
		
		if (!usuarioService.validaLogin(loginDTO))
			throw new NaoAutenticadoException("Login falhou", "Usuário não autenticado.");

		String token = geraToken(loginDTO.getEmail());
		return new LoginResponse(token);
	}

	private String geraToken(String email) {
		return Jwts.builder().setHeaderParam("typ", "JWT").setSubject(email)
				.signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 1000)).compact();
	}

	public String getSujeitoDoToken(String authorizationHeader) {
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
