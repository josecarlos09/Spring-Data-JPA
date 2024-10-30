package br.com.livraria.livraria.dto.livro;

import java.util.Set;
import java.util.UUID;

public record DadosCadastroLivro(String titulo,
                                 UUID editoraId,
                                 Set<UUID> autoresIds,
                                 String descricao) {
}