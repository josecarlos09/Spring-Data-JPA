package br.com.livraria.livraria.model.autor;


import br.com.livraria.livraria.model.livro.Livro;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Autor")
@Table(name = "TB_Autor")

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@EqualsAndHashCode(of = "id")// Indicaa a chave primary-key

public class Autor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome_autor", nullable = false, unique = true)
    private String nome;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Permições de escrita (evita problemas na serialização)
    @ManyToMany(mappedBy = "autor", fetch = FetchType.LAZY) // autor faz referencia a coleção de autores na entidade Autor
    private Set<Livro> livros = new HashSet<>(); // Criação da coleção de livros

}
