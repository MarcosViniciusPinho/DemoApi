package com.marcospinho.demo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@EqualsAndHashCode(exclude = {"nome", "sobrenome", "idade"})
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Setter
    @NotEmpty
    @Column(name = "nome", length = 30, nullable = false)
    private String nome;

    @Setter
    @Column(name = "sobrenome", length = 30)
    private String sobrenome;

    @NotNull
    @Column(name = "idade", nullable = false)
    private Integer idade;

    public void setIdade(Integer idade) {
        this.idade = idade != null && idade > 0 ? idade : null;
    }
}
