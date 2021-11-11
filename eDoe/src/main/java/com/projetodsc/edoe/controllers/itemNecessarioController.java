package com.projetodsc.edoe.controllers;

import java.util.List;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projetodsc.edoe.models.Descritor;
import com.projetodsc.edoe.models.dtos.ItemDTODeleted;
import com.projetodsc.edoe.models.dtos.ItemNecessarioDTO;
import com.projetodsc.edoe.models.dtos.ItemNecessarioDTOResponse;
import com.projetodsc.edoe.services.ItemService;

@RestController
@RequestMapping("/api/itens")
public class itemNecessarioController {
	
	@Autowired
	ItemService itemService;
	
	@PostMapping("/necessarios/cadastro")
	public ResponseEntity<ItemNecessarioDTOResponse> adicionaItemNecessario(@RequestBody ItemNecessarioDTO itemDTO, @RequestHeader("Authorization") String header) throws ServletException{
		return new ResponseEntity<ItemNecessarioDTOResponse>(itemService.addItemNecessario(itemDTO, header), HttpStatus.OK);
	}
	
	@PutMapping("/necessarios/{id}")
	public ResponseEntity<ItemNecessarioDTOResponse> atualizaItem(@PathVariable long id, @RequestBody ItemNecessarioDTO itemAtualizado, @RequestHeader("Authorization") String authHeader) throws ServletException {
		return new ResponseEntity<ItemNecessarioDTOResponse>(itemService.atualizaItemNecessario(id, itemAtualizado, authHeader), HttpStatus.OK);
	}
	
	@DeleteMapping("/necessarios/{id}")
	public ResponseEntity<ItemDTODeleted> removeItem(@PathVariable("id") long id_item, @RequestHeader("Authorization") String header) throws ServletException{
		return new ResponseEntity<ItemDTODeleted>(itemService.removeItemNecessario(id_item, header), HttpStatus.OK);
	}
	
	@GetMapping("/necessarios/{descritor}")
	public ResponseEntity<List<ItemNecessarioDTOResponse>> getByDescritor(@PathVariable String descritor) throws ServletException {
		return new ResponseEntity<List<ItemNecessarioDTOResponse>>(itemService.getItensNecessarioByDescritor(new Descritor(descritor)), HttpStatus.OK);
	}
	
	@GetMapping("/necessarios/ranking-quantidade")
	public ResponseEntity<List<ItemNecessarioDTOResponse>> getItensNecessarioOrderByQuantidadeDesc() throws ServletException{
		return new ResponseEntity<List<ItemNecessarioDTOResponse>>(itemService.getItensNecessarioOrderByQuantidadeDesc(), HttpStatus.OK);
	}
	
	@GetMapping("/necessarios/buscar={string_busca}")
	public ResponseEntity<List<ItemNecessarioDTOResponse>> getByParametroBusca(@PathVariable String string_busca) throws ServletException {
		return new ResponseEntity<List<ItemNecessarioDTOResponse>>(itemService.getItensNecessarioByString_busca(string_busca), HttpStatus.OK);
	}
	
}
