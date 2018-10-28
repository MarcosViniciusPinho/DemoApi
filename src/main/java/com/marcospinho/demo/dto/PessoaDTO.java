package com.marcospinho.demo.dto;

import com.marcospinho.demo.entity.Pessoa;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PessoaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private String sobrenome;

    private Integer idade;

    public Pessoa toEntity() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(this.getId());
        pessoa.setNome(this.getNome());
        pessoa.setSobrenome(this.getSobrenome());
        pessoa.setIdade(this.getIdade());
        return pessoa;
    }

}
