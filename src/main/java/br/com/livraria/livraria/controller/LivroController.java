package br.com.livraria.livraria.controller;

import br.com.livraria.livraria.dto.livro.DadosAtualizacaoLivro;
import br.com.livraria.livraria.dto.livro.DadosCadastroLivro;
import br.com.livraria.livraria.model.livro.Livro;
import br.com.livraria.livraria.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/livraria/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping("/consultar")
    public ResponseEntity<List<Livro>> lisarLivros() {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.listarLivros());
    }

    @PostMapping("/savar")
    public ResponseEntity<Livro> salvarLivro(@RequestBody DadosCadastroLivro livroRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.salvarLivros(livroRecordDto));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarLivro(@PathVariable UUID id) {
        livroService.deletarLivro(id);
        return ResponseEntity.status(HttpStatus.OK).body("Livro deletado com sucesso.");
    }

    @DeleteMapping("/inexistente/{id}")
    @Transactional
    public ResponseEntity exclusaoLogica(@PathVariable UUID id){
        return livroService.inexistente(id);
    }

    @GetMapping("/consulta_detalhada/{id}")
    public ResponseEntity consultaDetalhada(@PathVariable UUID id){
        return livroService.consultaDetalhada(id);
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoLivro atualizarDados){
        return livroService.modificar(atualizarDados);
    }
}