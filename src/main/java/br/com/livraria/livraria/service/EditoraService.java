package br.com.livraria.livraria.service;

import br.com.livraria.livraria.dto.editora.DadosCadastrarEditor;
import br.com.livraria.livraria.model.editora.Editora;
import br.com.livraria.livraria.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditoraService {
    @Autowired
    private EditoraRepository editoraRepository;

    public Editora salvarEditora(DadosCadastrarEditor editoraDto) {

        Editora editora = new Editora();

        editora.setNome(editoraDto.nome());
        return editoraRepository.save(editora);
    }
}
