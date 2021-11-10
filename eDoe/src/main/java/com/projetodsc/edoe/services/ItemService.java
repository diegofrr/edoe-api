package com.projetodsc.edoe.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetodsc.edoe.exceptions.DescritorInvalidoException;
import com.projetodsc.edoe.exceptions.DescritorNaoExisteException;
import com.projetodsc.edoe.exceptions.ItemNaoEncontradoException;
import com.projetodsc.edoe.exceptions.NaoAutorizadoException;
import com.projetodsc.edoe.exceptions.UsuarioInvalidoException;
import com.projetodsc.edoe.models.Descritor;
import com.projetodsc.edoe.models.ItemDoacao;
import com.projetodsc.edoe.models.TipoUsuario;
import com.projetodsc.edoe.models.Usuario;
import com.projetodsc.edoe.models.dtos.ItemDTODeleted;
import com.projetodsc.edoe.models.dtos.ItemDoacaoDTO;
import com.projetodsc.edoe.models.dtos.DoadorDTOResponse;
import com.projetodsc.edoe.models.dtos.ItemDTOResponse;
import com.projetodsc.edoe.repositories.DescritoresRepository;
import com.projetodsc.edoe.repositories.ItensRepository;
import com.projetodsc.edoe.repositories.UsuariosRepository;

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
	
	public List<ItemDTOResponse> getItensOrderByQuantidadeDesc(){
		List<ItemDoacao> itens = itensRepositorio.findTop10ByOrderByQuantidadeDoacaoDesc().get();
		List<ItemDTOResponse> listResponse = new ArrayList<>();
		for (ItemDoacao i : itens) {
			ItemDTOResponse newItem = new ItemDTOResponse(i, i.getDoador());
			listResponse.add(newItem);
		}
		return listResponse;
	}
	
	public ItemDoacao alteraDados(ItemDoacao item, ItemDoacao itemAtt) {
		item.setNome(itemAtt.getNome());
		item.setDescricaoDetalhada(itemAtt.getDescricaoDetalhada());
		item.setQuantidadeDoacao(itemAtt.getQuantidadeDoacao());
		item.setDescritor(itemAtt.getDescritor());
		return item;
	}
	
	public ItemDoacao atualizaItem(long id, ItemDoacaoDTO itemAtualizado, String authHeader) {
		itemAtualizado.setNome(itemAtualizado.getNome().toUpperCase());
		itemAtualizado.setDescricaoDetalhada(itemAtualizado.getDescricaoDetalhada().toUpperCase());
		
		if (!itensRepositorio.existsById(id))
			throw new ItemNaoEncontradoException("Item não encontrado!", "Nenhum item com o id " + id + " no sistema.");
		ItemDoacao item = itensRepositorio.findById(id).get();
		String subject = jwtService.getSujeitoDoToken(authHeader);
		Optional<Usuario> usuarioDoToken = usuariosRepositorio.findByEmail(subject);
		if (usuarioDoToken.get() != item.getDoador())
			throw new NaoAutorizadoException("Não autorizado", "Este item pertence a outro doador.");
		return itensRepositorio.save(alteraDados(item, itemAtualizado.getItem()));
	}
	
	public List<ItemDTOResponse> getItensByDescritor(Descritor descritor){
		descritor.setDescricao(descritor.getDescricao().toUpperCase());
		
		if (!descritoresRepositorio.existsByDescricao(descritor.getDescricao().toUpperCase()))
			throw new DescritorInvalidoException("Descritor inválido", "Este descritor não existe no sistema.");

		List<ItemDTOResponse> response = new ArrayList<>();
		for (ItemDoacao i : itensRepositorio.findByDescritor(descritor).get()) {
			response.add(new ItemDTOResponse(i, i.getDoador()));
		}
		return response;
		
		
	}
		
	public ItemDTOResponse addItem(ItemDoacaoDTO itemDTO, String authHeader) {
		itemDTO.setNome(itemDTO.getNome().toUpperCase());
		itemDTO.setDescricaoDetalhada(itemDTO.getDescricaoDetalhada().toUpperCase());
		
		String subject = jwtService.getSujeitoDoToken(authHeader);
		Optional<Usuario> usuarioDoToken = usuariosRepositorio.findByEmail(subject);
		
		if (!descritoresRepositorio.existsByDescricao(itemDTO.getDescritor().getDescricao()))
			throw new DescritorNaoExisteException("Descritor não existe", "Este descritor não está cadastrado no sistema.");
		
		if (usuarioDoToken.get().getTipo() != TipoUsuario.DOADOR_RECEPTOR && usuarioDoToken.get().getTipo() != TipoUsuario.DOADOR && usuarioDoToken.get().getTipo() != TipoUsuario.ADMIN) {
			throw new NaoAutorizadoException("Usuário não autorizado", "Você precisar ser um doador para utilizar esta funcionalidade.");
		}
		
		itemDTO.setDescritor(descritoresRepositorio.findByDescricao(itemDTO.getDescritor().getDescricao()).get());
		ItemDoacao item = itemDTO.getItem();
		item.setDoador(usuarioDoToken.get());
		itensRepositorio.save(item);
		return new ItemDTOResponse(item, item.getDoador());
	}

	public List<ItemDoacao> getItens() {
		return itensRepositorio.findAll();
	}
	
	public ItemDTODeleted removeItem(long id, String authHeader) {
		String subject = jwtService.getSujeitoDoToken(authHeader);
		Optional<Usuario> usuarioDoToken = usuariosRepositorio.findByEmail(subject);
		
		if (!itensRepositorio.existsById(id))
			throw new ItemNaoEncontradoException("Item não encontrado!", "Nenhum item com o id " + id + " no sistema.");
		
		if (usuarioDoToken.get() != verificaDoadorDoItem(id)) 
			throw new NaoAutorizadoException("Usuário não autorizado!", "Este item é de propriedade de outro usuário.");
		
		ItemDoacao itemResponse = itensRepositorio.findById(id).get();
		itensRepositorio.delete(itemResponse);
		return new ItemDTODeleted(itemResponse.getNome(), itemResponse.getDescricaoDetalhada());
		
	}
	
	public Usuario verificaDoadorDoItem(long id_item) {
		return itensRepositorio.findById(id_item).get().getDoador();
	}
	
}
