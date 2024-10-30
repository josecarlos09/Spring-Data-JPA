package br.com.livraria.livraria.controller;

import br.com.livraria.livraria.dto.editora.DadosCadastrarEditor;
import br.com.livraria.livraria.model.editora.Editora;
import br.com.livraria.livraria.service.EditoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/livraria/editora")
public class EditorController {

    @Autowired
    private EditoraService editoraService;

    @PostMapping("/savar")
    public ResponseEntity<Editora> salvarEditor(@RequestBody DadosCadastrarEditor editorDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(editoraService.salvarEditora(editorDto));
    }

}
