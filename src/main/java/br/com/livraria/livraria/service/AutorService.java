package br.com.livraria.livraria.service;

import br.com.livraria.livraria.dto.autor.DadosCadastroAutor;
import br.com.livraria.livraria.model.autor.Autor;
import br.com.livraria.livraria.repository.AutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Transactional
    public Autor salvarAutor(DadosCadastroAutor autorDto) {
        Autor autor = new Autor(); // Instanciando o objeto autor

        autor.setNome(autorDto.nome());
        return autorRepository.save(autor); // As informações do autor seram salvas
    }
}
