package com.projetodsc.edoe.filters;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.PrematureJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;
import com.projetodsc.edoe.services.JWTService;

public class FiltersToken extends GenericFilterBean {

	public final static int TOKEN_INDEX = 7;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String header = req.getHeader("Authorization");

		if (header == null || !header.startsWith("Bearer ")) {
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED,
					"Token inexistente ou mal formatado");

			return;
		}

		String token = header.substring(TOKEN_INDEX);
		try {
			Jwts.parser().setSigningKey(JWTService.TOKEN_KEY).parseClaimsJws(token).getBody();
		} catch (SignatureException | ExpiredJwtException | MalformedJwtException | PrematureJwtException
				| UnsupportedJwtException | IllegalArgumentException e) {
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
			return;
		}
		chain.doFilter(request, response);
	}

}
