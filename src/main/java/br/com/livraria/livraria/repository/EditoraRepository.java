package br.com.livraria.livraria.repository;

import br.com.livraria.livraria.model.editora.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EditoraRepository extends JpaRepository<Editora, UUID> {
}
