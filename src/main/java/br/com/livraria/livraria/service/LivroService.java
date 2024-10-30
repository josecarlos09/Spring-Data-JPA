package br.com.livraria.livraria.service;

import br.com.livraria.livraria.dto.livro.DadosAtualizacaoLivro;
import br.com.livraria.livraria.dto.livro.DadosDetalhamentoLivro;
import br.com.livraria.livraria.dto.livro.DadosCadastroLivro;
import br.com.livraria.livraria.model.editora.Editora;
import br.com.livraria.livraria.model.livro.Livro;
import br.com.livraria.livraria.model.resumo.Resumo;
import br.com.livraria.livraria.repository.AutorRepository;
import br.com.livraria.livraria.repository.EditoraRepository;
import br.com.livraria.livraria.repository.LivroRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private  AutorRepository autorRepository;
    @Autowired
    private  EditoraRepository editoraRepository;

    public List<Livro> listarLivros() {
        /*
        O .findAll() listara todos os livros
         */
        return livroRepository.findAll();
    }

    @Transactional // Essa anotação indica que está acontecedo um Início e Fim da Transação com uma base de dados
    public Livro salvarLivros(DadosCadastroLivro livroDto) {
        Livro livro = new Livro(); // Instanciando o objeto livro

        livro.setTitulo(livroDto.titulo()); // Recebendo o titulo via DTO
        livro.setEditora((Editora) editoraRepository.findById(livroDto.editoraId()).get()); // Recebendo a editora do repository via DTO
        livro.setAutor(autorRepository.findAllById(livroDto.autoresIds()).stream().collect(Collectors.toSet())); // Recebendo o autor do repository via DTO e convertendo para uma collect

        Resumo resumo = new Resumo(); // Instanciando o objeto Resumo
        livro.setResumo(resumo); // o livro contera um resumo

        resumo.setDescricao(livroDto.descricao()); // Recebendo um comentario
        resumo.setLivro(livro);// O resumo tem um livro

        return livroRepository.save(livro); // As informações do livro seram salvas
    }

    @Transactional
    public void deletarLivro(UUID id){
        livroRepository.deleteById(id); // Será excluido um determinado livro pelo seu id juntamente com o resumo do livro.
    }

    public ResponseEntity inexistente(@PathVariable UUID id){
        /* Para indicar que o livro está inexistente
        1 - Foi adicionado um campo na tb_livro chamada existente.
        2 - Foi criada uma função inexistente na entidade Livro, que torna o livro existente em condição falso.
        */
        var livroInexistente = livroRepository.getReferenceById(id);//Fara referência a tabela que possui o id informado.
        livroInexistente.inexistente();// O método inativar está na entidade medico, transformando o ativo igual a false.

        return ResponseEntity.noContent().build(); // Indica requisição processada e corpo vazio.
    }

    public ResponseEntity consultaDetalhada(@PathVariable UUID id){
        /* O método consultaDetalhada recebe um DTO que será passado para o usuário que fizer a requisição
         * Por que não usar o mesmo DTO do cadastro? Não utilizei o mesmo DTO do cadastro, porque terá informações cadastradas que não serão informadas ao usuário.
         */
        var livro = livroRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoLivro(livro));
    }

    public ResponseEntity modificar(@RequestBody @Valid DadosAtualizacaoLivro atualizarDados){
        /* As informações que serão alteradas são especificada pelo DTO DadosAtualizacaoLivro
         * Para identificar o registro que será atualizado será usado a chave primaria da tabela, que é o id.
         */
        var livro = livroRepository.getReferenceById(atualizarDados.id());
        livro.atualizarInformacoes(atualizarDados);

        return ResponseEntity.ok(new DadosDetalhamentoLivro(livro));
    }
}