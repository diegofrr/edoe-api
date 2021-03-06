package com.apiedoe.controllers;

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
import com.apiedoe.models.Descritor;
import com.apiedoe.models.TipoItem;
import com.apiedoe.models.requestModels.ItemRequest;
import com.apiedoe.models.responseModels.ItemResponse;
import com.apiedoe.services.ItemService;

@RestController
@RequestMapping("/api/itens")
public class ItemNecessarioController {

	@Autowired
	ItemService itemService;

	@PostMapping("/necessarios/cadastro")
	public ResponseEntity<ItemResponse> adicionaItemNecessario(@RequestBody ItemRequest itemDTO,
			@RequestHeader("Authorization") String header) throws ServletException {
		return new ResponseEntity<ItemResponse>(itemService.adicionaItem(itemDTO, TipoItem.NECESSARIO, header),
				HttpStatus.CREATED);
	}

	@PutMapping("/necessarios/atualizar/{id}")
	public ResponseEntity<ItemResponse> atualizaItem(@PathVariable long id, @RequestBody ItemRequest itemAtualizado,
			@RequestHeader("Authorization") String authHeader) throws ServletException {
		return new ResponseEntity<ItemResponse>(
				itemService.atualizaItem(id, itemAtualizado, TipoItem.NECESSARIO, authHeader), HttpStatus.OK);
	}

	@DeleteMapping("/necessarios/remover/{id}")
	public ResponseEntity<ItemResponse> removeItem(@PathVariable("id") long id,
			@RequestHeader("Authorization") String header) throws ServletException {
		return new ResponseEntity<ItemResponse>(itemService.removeItem(id, header), HttpStatus.OK);
	}

	@GetMapping("/necessarios/descritor={descritor}")
	public ResponseEntity<List<ItemResponse>> getByDescritor(@PathVariable String descritor) throws ServletException {
		return new ResponseEntity<List<ItemResponse>>(
				itemService.getItensByDescritor(new Descritor(descritor), TipoItem.NECESSARIO), HttpStatus.OK);
	}

	@GetMapping("/necessarios/ranking-quantidade")
	public ResponseEntity<List<ItemResponse>> getItensNecessarioOrderByQuantidadeDesc() throws ServletException {
		return new ResponseEntity<List<ItemResponse>>(itemService.getItensOrderByQuantidadeDesc(TipoItem.NECESSARIO),
				HttpStatus.OK);
	}

	@GetMapping("/necessarios/buscar={stringBusca}")
	public ResponseEntity<List<ItemResponse>> getByParametroBusca(@PathVariable String stringBusca)
			throws ServletException {
		return new ResponseEntity<List<ItemResponse>>(
				itemService.getItensByStringBusca(stringBusca, TipoItem.NECESSARIO), HttpStatus.OK);
	}

	@GetMapping("/necessarios/matches/{id}")
	public ResponseEntity<List<ItemResponse>> matches(@PathVariable("id") long id,
			@RequestHeader("Authorization") String header) throws ServletException {
		return new ResponseEntity<List<ItemResponse>>(itemService.matchesById(id, header), HttpStatus.OK);
	}

}
