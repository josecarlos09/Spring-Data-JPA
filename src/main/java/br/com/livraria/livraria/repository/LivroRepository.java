package br.com.livraria.livraria.repository;

import br.com.livraria.livraria.model.livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LivroRepository extends JpaRepository<Livro, UUID> {

    // A Query de consulta é realizada pelo Spring Data JPA usando o atribulto titulo da entidade Livro
    // Estrutura: find<Entidade>By<Atribulto>(<Tipo do campo> <campo>)
    Livro findLivroByTitulo(String titulo);

    // nativeQuery = true permite usarmos Querys do SGBD MySQL
    @Query(value = "SELECT * FROM tb_livro WHERE editora_id = :editoraId", nativeQuery = true)
    List<Livro> findLivroByEditoraId(@Param("editoraId") UUID editoraId);
}
