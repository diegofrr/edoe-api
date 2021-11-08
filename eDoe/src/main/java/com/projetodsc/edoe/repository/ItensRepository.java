package com.projetodsc.edoe.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projetodsc.edoe.model.Item;

@Repository
public interface ItensRepository extends JpaRepository<Item, Long>{
	
	boolean existsById(long id);
	
	
}
