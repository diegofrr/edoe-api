package com.apiedoe.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.apiedoe.models.Usuario;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, String> {
	
	Optional<Usuario> findByEmailIgnoreCase(String email);
	boolean existsByEmailIgnoreCase(String email); 

}