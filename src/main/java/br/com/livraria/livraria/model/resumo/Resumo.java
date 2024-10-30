package br.com.livraria.livraria.model.resumo;

import br.com.livraria.livraria.model.livro.Livro;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity(name = "Resumo")
@Table(name = "TB_Resumo")

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@EqualsAndHashCode(of = "id")// Indicaa a chave primary-key

public class Resumo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "descricao", nullable = false, unique = true)
    private String descricao;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    @JoinColumn(name = "livro_id") // Vai indicar qual livro o resumo pertence (criar um campo na entidade resumo chamdo livro_id)
    private Livro livro;

}