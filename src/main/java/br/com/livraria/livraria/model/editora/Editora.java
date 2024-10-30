package br.com.livraria.livraria.model.editora;

import br.com.livraria.livraria.model.livro.Livro;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Editora")
@Table(name = "TB_Editora")

@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter

@EqualsAndHashCode(of = "id")// Indicaa a chave primary-key

public class Editora implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @Column(name = "nome_editora", nullable = false, unique = true)
    private String nome;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //Apenas de escrita (Evita erros de serializações).
    // Equema de relacionamento (1 para M)
    @OneToMany(mappedBy = "editora", fetch = FetchType.LAZY) // FetchType.LAZY indica carregamento preguisozo (só vai buscar a editora referente ao livro quando for necessario)
    private Set<Livro> livro = new HashSet<>(); // Coleção de livros que a editora terá
}
