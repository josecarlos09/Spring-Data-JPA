package br.com.livraria.livraria.repository;

import br.com.livraria.livraria.model.resumo.Resumo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResumoRepository extends JpaRepository<Resumo, UUID> {
}
