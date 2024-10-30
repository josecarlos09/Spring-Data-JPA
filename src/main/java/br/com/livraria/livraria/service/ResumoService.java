package br.com.livraria.livraria.service;

import br.com.livraria.livraria.dto.resumo.DadosCadastroResumo;
import br.com.livraria.livraria.model.resumo.Resumo;
import br.com.livraria.livraria.repository.LivroRepository;
import br.com.livraria.livraria.repository.ResumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumoService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ResumoRepository resumoRepository;

    public void salvarResumo(DadosCadastroResumo resumoDto) {
        Resumo resumo = new Resumo();

        resumo.setDescricao(resumoDto.descricao());
        resumo.setLivro(livroRepository.findById(resumoDto.livroId()).orElse(null));

        resumoRepository.save(resumo);
    }
}
