package br.com.livraria.livraria.dto.livro;

import br.com.livraria.livraria.dto.autor.DadosCadastroAutor;
import br.com.livraria.livraria.model.editora.Editora;
import br.com.livraria.livraria.model.livro.Livro;
import br.com.livraria.livraria.model.resumo.Resumo;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record DadosDetalhamentoLivro(UUID id, String titulo, Boolean existente, Editora editora, Set<DadosCadastroAutor> autor, Resumo resumo){
    public DadosDetalhamentoLivro (Livro livro){
        this(
                livro.getId(), // Id do livro
                livro.getTitulo(), // Titulo do livro
                livro.getExistente(), // Se o livro existe na livraria
                livro.getEditora(), // Dados da editora {Id, nome}

                /* Para que se consigua ter as informações do autor que esta em uma coleção
                 * o fetch do autor tem que ser EAGER (FetchType.EAGER), não pode ser carregamento preguisozo LAZY
                 */
                livro.getAutor().stream() // O método getAutores() retorna Set<Autor>
                        .map(autor -> new DadosCadastroAutor(autor.getNome())) // Convertendo cada Autor para AutorCadastroDto e pega o nome
                        .collect(Collectors.toSet()), // Coletando em um Set.
                livro.getResumo()
        );
    }
}

