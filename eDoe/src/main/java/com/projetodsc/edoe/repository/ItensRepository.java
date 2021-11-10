package com.projetodsc.edoe.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projetodsc.edoe.model.Descritor;
import com.projetodsc.edoe.model.Item;

@Repository
public interface ItensRepository extends JpaRepository<Item, Long>{
	
	boolean existsById(long id);
	Optional<List<Item>> findByDescritor(Descritor descritor);
	Optional<List<Item>> findTop10ByOrderByQuantidadeDoacaoDesc();
	
}
