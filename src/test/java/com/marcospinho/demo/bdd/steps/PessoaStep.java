package com.marcospinho.demo.bdd.steps;

import com.marcospinho.demo.entity.Pessoa;
import com.marcospinho.demo.service.PessoaService;
import com.marcospinho.demo.service.impl.PessoaServiceImpl;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

/**
 * @author Marcos Pinho
 */
public class PessoaStep {

    private PessoaService service;

    private Pessoa pessoa;

    @Before
    public void init() {
        this.pessoa = new Pessoa();
        this.service = new PessoaServiceImpl();
    }

    @Dado("^uma pessoa com o nome (.*)$")
    public void umaPessoaComONome(String nome) throws Throwable {
        this.pessoa.setNome(nome);
    }

    @Quando("^tento cadastrá-la$")
    public void tentoCadastraLa() throws Throwable {
        this.service.create(this.pessoa);
    }

    @Então("^Falha: Nome da pessoa é obrigatória$")
    public void falhaNomeDaPessoaEhObrigatoria() throws Throwable {
    }

    @Então("^Pessoa cadastrada com sucesso$")
    public void pessoaCadastradaComSucesso() throws Throwable {
    }

}