package com.marcospinho.demo.bdd.steps.pessoa;

import com.marcospinho.demo.bdd.steps.StepContext;
import com.marcospinho.demo.entity.Pessoa;
import com.marcospinho.demo.service.PessoaService;
import com.marcospinho.demo.service.exception.RecurseNotFoundException;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionSystemException;

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

    private String mensagemErroNegocio;

    @Before
    public void init() {
        this.pessoa = new Pessoa();
    }

    @Dado("^o identificador da pessoa (\\d+)$")
    public void oIdentificadorDaPessoa(Long identificador) throws Throwable {
        this.pessoa.setId(identificador);
    }

    @Dado("^uma pessoa com o nome ([a-z A-Z]*)$")
    public void umaPessoaComONome(String nome) throws Throwable {
        this.pessoa.setNome(nome);
    }

    @E("^com a idade (\\d*)$")
    public void comAIdade(Integer idade) throws Throwable {
        this.pessoa.setIdade(idade);
    }

    @Quando("^tento realizar o cadastro$")
    public void tentoCadastraLa() throws Throwable {
        try {
            this.service.create(this.pessoa);
        } catch (ConstraintViolationException e) {
            this.mensagensDeErro = e.getConstraintViolations();
        }
    }

    @Quando("^tento realizar a alteração$")
    public void tentoRealizarAAlteração() throws Throwable {
        try {
            this.service.update(this.pessoa.getId(), this.pessoa);
        } catch (TransactionSystemException e) {
            this.mensagensDeErro = ((ConstraintViolationException) e.getOriginalException().getCause()).getConstraintViolations();
        } catch (RecurseNotFoundException e) {
            this.mensagemErroNegocio = e.getLocalizedMessage();
        }
    }

    @Quando("^tento realizar a exclusão$")
    public void tentoRealizarAExclusão() throws Throwable {
        try {
            this.service.delete(this.pessoa.getId());
        } catch (TransactionSystemException e) {
            this.mensagensDeErro = ((ConstraintViolationException) e.getOriginalException().getCause()).getConstraintViolations();
        } catch (RecurseNotFoundException e) {
            this.mensagemErroNegocio = e.getLocalizedMessage();
        }
    }

    @Então("^Informações obrigatórias ainda não foram informadas$")
    public void informaçõesObrigatóriasAindaNãoForamInformadas() throws Throwable {
        Assert.assertTrue(!this.mensagensDeErro.isEmpty());
    }

    @Então("^Não existe pessoa com o identificador fornecido$")
    public void nãoExistePessoaComOIdentificadorFornecido() throws Throwable {
        Assert.assertEquals("Não foi encontrada uma pessoa com o id fornecido", this.mensagemErroNegocio);
    }

    @Então("^Operação realizada com sucesso")
    public void pessoaCadastradaComSucesso() throws Throwable {
        Assert.assertNull(this.mensagensDeErro);
    }

}