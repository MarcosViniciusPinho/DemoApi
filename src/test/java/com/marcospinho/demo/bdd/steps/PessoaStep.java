package com.marcospinho.demo.bdd.steps;

import com.marcospinho.demo.entity.Pessoa;
import com.marcospinho.demo.service.PessoaService;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @author Marcos Pinho
 */
public class PessoaStep extends StepContext {

    @Autowired
    private PessoaService service;

    private Pessoa pessoa;

    private Set<ConstraintViolation<?>> mensagensDeErro;

    @Before
    public void init() {
        this.pessoa = new Pessoa();
    }

    @Dado("^uma pessoa com o nome (.*)$")
    public void umaPessoaComONome(String nome) throws Throwable {
        this.pessoa.setNome(nome);
    }

    @E("^com a idade (\\d*)$")
    public void comAIdade(Integer idade) throws Throwable {
        this.pessoa.setIdade(idade);
    }

    @Quando("^tento cadastrá-la$")
    public void tentoCadastraLa() throws Throwable {
        try {
            this.service.create(this.pessoa);
        } catch (ConstraintViolationException e) {
            this.mensagensDeErro = e.getConstraintViolations();
        }
    }

    @Então("^Informações obrigatórias ainda não foram informadas$")
    public void informaçõesObrigatóriasAindaNãoForamInformadas() throws Throwable {
        Assert.assertTrue(!this.mensagensDeErro.isEmpty());
    }

    @Então("^Pessoa cadastrada com ([a-z A-Z]*)$")
    public void pessoaCadastradaComSucesso(String resultado) throws Throwable {
        Assert.assertEquals(resultado, "sucesso");
    }

}