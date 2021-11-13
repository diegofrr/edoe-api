package com.projetodsc.edoe.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projetodsc.edoe.models.Descritor;
import com.projetodsc.edoe.models.Item;
import com.projetodsc.edoe.models.TipoItem;

@Repository
public interface ItensRepository extends JpaRepository<Item, Long>{
	
	boolean existsById(long id);
	Optional<List<Item>> findByDescritor(Descritor descritor);
	Optional<List<Item>> findAllByDescritor(Descritor descritor);
	Optional<List<Item>> findAllByOrderByQuantidadeDesc();
	Optional<List<Item>> findAllByDescritorAndTipo(Descritor descritor, TipoItem tipo);
	boolean existsByDescritor(Descritor descritor);
	
}
