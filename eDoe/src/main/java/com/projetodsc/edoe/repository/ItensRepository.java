package com.projetodsc.edoe.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.projetodsc.edoe.model.DescricaoItem;
import com.projetodsc.edoe.model.Item;

public interface ItensRepository extends JpaRepository<Item, Long>{
	
	List<Item> findByDescricao(DescricaoItem descricao);
	List<Item> findByQuantidade(int quantidade);
	

}
