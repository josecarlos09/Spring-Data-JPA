package br.com.livraria.livraria.model.livro;

import br.com.livraria.livraria.dto.livro.DadosAtualizacaoLivro;
import br.com.livraria.livraria.model.autor.Autor;
import br.com.livraria.livraria.model.editora.Editora;
import br.com.livraria.livraria.model.resumo.Resumo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Livro")
@Table(name = "TB_Livro")

@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter
@EqualsAndHashCode(of = "id")// Indicaa a chave primary-key

public class Livro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)// Gera números sequenciais
    @Column(name = "Identificador")
    private UUID id; // o UUID é Identificadores próprios para arquiteturas distribuídas

    @Column(name = "titulo", nullable = false, unique = true)
    private String titulo;

    @Column(name = "existente")
    private Boolean existente=true;

    //Configuração dos graus de realcionamento de acordo com a modelagem do BD

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // (M para 1) Esquema de relacionamento de Livro com Editora
    @ManyToOne (fetch = FetchType.EAGER) // o FetchType.EAGER indica carregamento ansioso
    @JoinColumn(name = "editoraId") // Criação da chave estrangeira editoraId na tabela de Livro
    private Editora editora; // Indica qual é a entidade que estamos tratando no relacionamento é Editora

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    /* (M para M) Esquema de relacionamento de autor com livros
       Esse relacionamento cria uma coleção de ambos os lados, ou seja, essa relação cria uma tabela de relação)
       Por isso, será criada uma tabela auxiliar (A tabela de relacionamento será criada aqui e não na entidade autor
    */
    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable(
            name = "tb_livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"), // Vai criar um campo livro_id.
            inverseJoinColumns = @JoinColumn(name = "autor_id")) // vai criar um campo inverso autor_id que pertence a coleção referenciada abaixo
    private Set<Autor> autor = new HashSet<>(); // Cria uma coleção de autor

    // (1 para 1) Esquema de relacionamento livros com resumo
    @OneToOne(mappedBy = "livro", cascade = CascadeType.ALL) // O CascadeType.ALL realiza auterações em cascata na tabela resumo quando livro recebe auterações na estrutura, se esse campo for alterado o resumo tabem será.
    private Resumo resumo;

    public void inexistente() {
        // Indica se o livro existe na livraria.
        this.existente=false;
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoLivro atualizarDados) {
        // Se o título do livro existe, os campos informados pelo DTO poderão ser alterados.
        if (atualizarDados.titulo()!=null) {
            this.setTitulo(atualizarDados.titulo());
        }
    }
}
