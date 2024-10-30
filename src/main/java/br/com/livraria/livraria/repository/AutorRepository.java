package br.com.livraria.livraria.repository;

import br.com.livraria.livraria.model.autor.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {

}
