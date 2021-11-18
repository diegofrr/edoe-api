package com.apiedoe.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.apiedoe.models.Descritor;
import com.apiedoe.services.DescritorService;
import com.apiedoe.services.ItemService;

@RestController
@RequestMapping("/api")
public class DescritorController {

	@Autowired
	DescritorService descritorService;

	@Autowired
	ItemService itemService;

	@GetMapping("/descritores")
	public ResponseEntity<List<Descritor>> getDescritores() {
		return new ResponseEntity<List<Descritor>>(descritorService.getDescritores(), HttpStatus.OK);
	}

	@PostMapping("/descritores/cadastro")
	public ResponseEntity<Descritor> addDescritor(@RequestBody Descritor descritor) {
		return new ResponseEntity<Descritor>(descritorService.addDescritor(descritor), HttpStatus.CREATED);
	}
}
