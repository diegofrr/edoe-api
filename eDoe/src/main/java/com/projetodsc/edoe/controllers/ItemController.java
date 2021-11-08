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
import com.projetodsc.edoe.model.Descritor;
import com.projetodsc.edoe.model.Item;
import com.projetodsc.edoe.model.dto.ItemDTO;
import com.projetodsc.edoe.model.dto.ItemDTODeleted;
import com.projetodsc.edoe.model.dto.ResponseItemDTO;
import com.projetodsc.edoe.services.ItemService;

@RestController
@RequestMapping("/api")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@GetMapping("/itens")
	public ResponseEntity<List<Item>> getItens(){
		return new ResponseEntity<List<Item>>(itemService.getItens(), HttpStatus.OK);
	}

	
	@PostMapping("/itens/cadastrar")
	public ResponseEntity<Item> adicionaItem(@RequestBody ItemDTO itemDTO, @RequestHeader("Authorization") String header) throws ServletException{
		return new ResponseEntity<Item>(itemService.addItem(itemDTO, header), HttpStatus.OK);
	}
	
	@DeleteMapping("/itens/{id}")
	public ResponseEntity<ItemDTODeleted> removeItem(@PathVariable("id") long id_item, @RequestHeader("Authorization") String header) throws ServletException{
		return new ResponseEntity<ItemDTODeleted>(itemService.removeItem(id_item, header), HttpStatus.OK);
	}
	
	@GetMapping("/itens/buscar={descricao}")
	public ResponseEntity<List<ResponseItemDTO>> getByDescritor(@PathVariable String descricao) throws ServletException {
		return new ResponseEntity<List<ResponseItemDTO>>(itemService.getItensByDescritor(new Descritor(descricao)), HttpStatus.OK);
	}
	
	@PutMapping("/itens/{id}")
	public ResponseEntity<Item> atualizaItem(@PathVariable long id, @RequestBody ItemDTO itemAtualizado, @RequestHeader("Authorization") String authHeader) throws ServletException {
		return new ResponseEntity<Item>(itemService.atualizaItem(id, itemAtualizado, authHeader), HttpStatus.OK);
		
	}
	

}
