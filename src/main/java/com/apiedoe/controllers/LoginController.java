package com.apiedoe.controllers;

import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiedoe.models.requestModels.LoginRequest;
import com.apiedoe.models.responseModels.LoginResponse;
import com.apiedoe.services.JWTService;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private JWTService jwtService;

	@PostMapping("/auth/login")
	public ResponseEntity<LoginResponse> autentica(@RequestBody LoginRequest login) throws ServletException {
		return new ResponseEntity<LoginResponse>(jwtService.autentica(login), HttpStatus.OK);
	}

}