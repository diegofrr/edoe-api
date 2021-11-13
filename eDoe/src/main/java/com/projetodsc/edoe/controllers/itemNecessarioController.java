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
import com.projetodsc.edoe.models.TipoItem;
import com.projetodsc.edoe.models.dtos.ItemResponse;
import com.projetodsc.edoe.models.dtos.ItemDTO;
import com.projetodsc.edoe.services.ItemService;

@RestController
@RequestMapping("/api/itens")
public class itemNecessarioController {
	
	@Autowired
	ItemService itemService;
	
	@PostMapping("/necessarios/cadastro")
	public ResponseEntity<ItemResponse> adicionaItemNecessario(@RequestBody ItemDTO itemDTO, @RequestHeader("Authorization") String header) throws ServletException{
		return new ResponseEntity<ItemResponse>(itemService.adicionaItem(itemDTO, TipoItem.NECESSARIO, header), HttpStatus.OK);
	}
	
	@PutMapping("/necessarios/atualizar/{id}")
	public ResponseEntity<ItemResponse> atualizaItem(@PathVariable long id, @RequestBody ItemDTO itemAtualizado, @RequestHeader("Authorization") String authHeader) throws ServletException {
		return new ResponseEntity<ItemResponse>(itemService.atualizaItem(id, itemAtualizado, TipoItem.NECESSARIO, authHeader), HttpStatus.OK);
	}
	
	@DeleteMapping("/necessarios/remover/{id}")
	public ResponseEntity<ItemResponse> removeItem(@PathVariable("id") long id_item, @RequestHeader("Authorization") String header) throws ServletException{
		return new ResponseEntity<ItemResponse>(itemService.removeItem(id_item, header), HttpStatus.OK);
	}
	
	@GetMapping("/necessarios/descritor={descritor}")
	public ResponseEntity<List<ItemResponse>> getByDescritor(@PathVariable String descritor) throws ServletException {
		return new ResponseEntity<List<ItemResponse>>(itemService.getItensByDescritor(new Descritor(descritor), TipoItem.NECESSARIO), HttpStatus.OK);
	}
	
	@GetMapping("/necessarios/ranking-quantidade")
	public ResponseEntity<List<ItemResponse>> getItensNecessarioOrderByQuantidadeDesc() throws ServletException{
		return new ResponseEntity<List<ItemResponse>>(itemService.getItensOrderByQuantidadeDesc(TipoItem.NECESSARIO), HttpStatus.OK);
	}
	
	@GetMapping("/necessarios/buscar={stringBusca}")
	public ResponseEntity<List<ItemResponse>> getByParametroBusca(@PathVariable String stringBusca) throws ServletException {
		return new ResponseEntity<List<ItemResponse>>(itemService.getItensByStringBusca(stringBusca, TipoItem.NECESSARIO), HttpStatus.OK);
	}
	
}
