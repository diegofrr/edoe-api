package com.apiedoe.services;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apiedoe.exceptions.NaoAutenticadoException;
import com.apiedoe.filters.FiltersToken;
import com.apiedoe.models.requestModels.LoginRequest;
import com.apiedoe.models.responseModels.LoginResponse;

@Service
public class JWTService {

	@Autowired
	private UsuarioService usuarioService;
	public static final String TOKEN_KEY = "wdsjfhkwbfdgwuierhweij";

	public LoginResponse autentica(LoginRequest login) {
		if (!usuarioService.validaLogin(login))
			throw new NaoAutenticadoException("Login falhou", "Usuário não autenticado.");

		String token = geraToken(login.getEmail());
		return new LoginResponse(token);
	}

	private String geraToken(String email) {
		return Jwts.builder().setHeaderParam("typ", "JWT").setSubject(email)
				.signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000)).compact();																								// token.
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
