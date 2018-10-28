package com.marcospinho.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.marcospinho.demo.entity.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PessoaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private String sobrenome;

    private Integer idade;

    public Pessoa toEntity() {
        Pessoa pessoa = new Pessoa(this.id, this.nome, this.sobrenome, null);
        pessoa.setIdade(this.getIdade());
        return pessoa;
    }

}
