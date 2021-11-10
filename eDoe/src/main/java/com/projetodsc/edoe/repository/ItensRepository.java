package com.projetodsc.edoe.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projetodsc.edoe.model.Descritor;
import com.projetodsc.edoe.model.ItemDoacao;

@Repository
public interface ItensRepository extends JpaRepository<ItemDoacao, Long>{
	
	boolean existsById(long id);
	Optional<List<ItemDoacao>> findByDescritor(Descritor descritor);
	Optional<List<ItemDoacao>> findTop10ByOrderByQuantidadeDoacaoDesc();
	
}
