package br.com.livraria.livraria.controller;

import br.com.livraria.livraria.dto.autor.DadosCadastroAutor;
import br.com.livraria.livraria.model.autor.Autor;
import br.com.livraria.livraria.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/livraria/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping("/savar")
    public ResponseEntity<Autor> salvarAutor(@RequestBody DadosCadastroAutor autorDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(autorService.salvarAutor(autorDto));
    }
}