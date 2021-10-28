package com.projetodsc.edoe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetodsc.edoe.models.Usuario;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
	public Usuario findByEmail(String email); // sem Optional
	public Optional<Usuario> findByNome(String nome); // usando Optional
	public List<Usuario> findByNomeContaining(String padrao);
	public boolean existsByNome(String nome);

}
