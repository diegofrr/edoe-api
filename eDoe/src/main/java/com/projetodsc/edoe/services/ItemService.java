package com.projetodsc.edoe.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetodsc.edoe.exception.DescritorInvalidoException;
import com.projetodsc.edoe.exception.DescritorNaoExisteException;
import com.projetodsc.edoe.exception.ItemNaoEncontradoException;
import com.projetodsc.edoe.exception.NaoAutorizadoException;
import com.projetodsc.edoe.model.Descritor;
import com.projetodsc.edoe.model.Item;
import com.projetodsc.edoe.model.Usuario;
import com.projetodsc.edoe.model.dto.ItemDTO;
import com.projetodsc.edoe.model.dto.ItemDTODeleted;
import com.projetodsc.edoe.model.dto.ResponseDoadorDTO;
import com.projetodsc.edoe.model.dto.ResponseItemDTO;
import com.projetodsc.edoe.repository.DescritoresRepository;
import com.projetodsc.edoe.repository.ItensRepository;
import com.projetodsc.edoe.repository.UsuariosRepository;

@Service
public class ItemService {

	@Autowired
	private ItensRepository itensRepositorio;
	
	@Autowired
	private DescritoresRepository descritoresRepositorio;
	
	@Autowired
	private UsuariosRepository usuariosRepositorio;
	
	@Autowired
	private JWTService jwtService;
	
	public List<ResponseItemDTO> getItensByDescritor(Descritor descritor){
		if (!descritoresRepositorio.existsByDescricao(descritor.getDescricao()))
			throw new DescritorInvalidoException("Descritor inválido", "Este descritor não existe no sistema.");

		List<ResponseItemDTO> response = new ArrayList<>();
		for (Item i : itensRepositorio.findByDescritor(descritor).get()) {
			response.add(new ResponseItemDTO(i, new ResponseDoadorDTO(i.getDoador())));
		}
		return response;
		
		
	}
		
	public Item addItem(ItemDTO itemDTO, String authHeader) {
		String subject = jwtService.getSujeitoDoToken(authHeader);
		Optional<Usuario> usuarioDoToken = usuariosRepositorio.findByEmail(subject);
		
		if (!descritoresRepositorio.existsByDescricao(itemDTO.getDescritor().getDescricao()))
			throw new DescritorNaoExisteException("Descritor não existe", "Este descritor não está cadastrado no sistema.");
		
		itemDTO.setDescritor(descritoresRepositorio.findByDescricao(itemDTO.getDescritor().getDescricao()).get());
		Item item = itemDTO.getItem();
		item.setDoador(usuarioDoToken.get());
		return itensRepositorio.save(item);
	}

	public List<Item> getItens() {
		return itensRepositorio.findAll();
	}
	
	public ItemDTODeleted removeItem(long id, String authHeader) {
		String subject = jwtService.getSujeitoDoToken(authHeader);
		Optional<Usuario> usuarioDoToken = usuariosRepositorio.findByEmail(subject);
		
		if (!itensRepositorio.existsById(id))
			throw new ItemNaoEncontradoException("Item não encontrado!", "Nenhum item com o id " + id + " no sistema.");
		
		if (usuarioDoToken.get() != verificaDoadorDoItem(id)) 
			throw new NaoAutorizadoException("Usuário não autorizado!", "Este item é de propriedade de outro usuário.");
		
		Item itemResponse = itensRepositorio.findById(id).get();
		itensRepositorio.delete(itemResponse);
		return new ItemDTODeleted(itemResponse.getNome(), itemResponse.getDescricaoDetalhada());
		
	}
	
	public Usuario verificaDoadorDoItem(long id_item) {
		return itensRepositorio.findById(id_item).get().getDoador();
	}
	
}
