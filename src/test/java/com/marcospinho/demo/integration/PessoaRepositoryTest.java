package com.marcospinho.demo.integration;

import com.marcospinho.demo.entity.Pessoa;
import com.marcospinho.demo.repository.PessoaRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author Marcos Pinho
 */
@RunWith(SpringRunner.class)
public class PessoaRepositoryTest extends IntegrationSource {

    @Autowired
    private PessoaRepository repository;

    @Test
    public void testFindAll_Sucess() {
        Pessoa pessoaSalva = this.repository.save(new Pessoa(null, "Marcos", "Pinho", 32));
        List<Pessoa> pessoasEsperadas = Arrays.asList(pessoaSalva);
        Optional<List<Pessoa>> lista = Optional.of(this.repository.findAll());
        Assert.assertEquals(pessoasEsperadas, lista.get());
    }

    @Test
    public void testFindById_Sucess() {
        Pessoa pessoaEsperada = new Pessoa(1L, "Marcos", "Pinho", 32);
        Assert.assertEquals(pessoaEsperada, this.repository.findById(1L).get());
    }

    @Test(expected = NoSuchElementException.class)
    public void testFindById_Exception() {
        this.repository.findById(10L).get();
    }

    @Test
    public void testSave_SucessCreate() {
        Pessoa pessoaEsperada = new Pessoa(2L, "Sabrina", "Cartaxo", 25);
        Pessoa pessoaSalva = this.repository.save(new Pessoa(null, "Sabrina", "Cartaxo", 25));
        Assert.assertEquals(pessoaEsperada, pessoaSalva);
    }

    @Test
    public void testSave_SucessCreateComSobrenomeEmBranco() {
        Pessoa pessoaEsperada = new Pessoa(3L, "Sabrina", null, 25);
        Pessoa pessoaSalva = this.repository.save(new Pessoa(null, "Sabrina", null, 25));
        Assert.assertEquals(pessoaEsperada, pessoaSalva);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testSave_ExceptionCreateSemNome() {
        this.repository.save(new Pessoa(null, null, "Cartaxo", 25));
    }

    @Test(expected = ConstraintViolationException.class)
    public void testSave_ExceptionCreateSemIdade() {
        this.repository.save(new Pessoa(null, "Sabrina", "Cartaxo", null));
    }

    @Test
    public void testSave_SucessUpdate() {
        Optional<Pessoa> pessoa = this.repository.findById(2L);
        pessoa.get().setNome("Sabrina2");
        pessoa.get().setSobrenome("Cartaxo2");

        Pessoa pessoaSalva = this.repository.save(pessoa.get());
        Assert.assertEquals(pessoa.get(), pessoaSalva);
    }

    @Test
    public void testSave_SucessUpdateComSobrenomeEmBranco() {
        Optional<Pessoa> pessoa = this.repository.findById(2L);
        pessoa.get().setNome("Sabrina3");
        pessoa.get().setSobrenome(null);

        Pessoa pessoaSalva = this.repository.save(pessoa.get());
        Assert.assertEquals(pessoa.get(), pessoaSalva);
    }

    @Test(expected = NoSuchElementException.class)
    public void testSave_ExceptionUpdateQuandoNaoExistePessoa() {
        Optional<Pessoa> pessoa = this.repository.findById(10L);
        this.repository.save(pessoa.get());
    }

    @Test(expected = TransactionSystemException.class)
    public void testSave_ExceptionUpdateSemNome() {
        Optional<Pessoa> pessoa = this.repository.findById(2L);
        pessoa.get().setNome(null);

        this.repository.save(pessoa.get());
    }

    @Test(expected = NoSuchElementException.class)
    public void testSave_ExceptionUpdateSemIdade() {
        Optional<Pessoa> pessoa = this.repository.findById(2L);
        pessoa.get().setIdade(null);

        this.repository.save(pessoa.get());
    }

    @Test
    public void testDelete_Sucess() {
        Optional<Pessoa> pessoa = this.repository.findById(1L);
        this.repository.delete(pessoa.get());
    }

    @Test(expected = NoSuchElementException.class)
    public void testDelete_ExceptionQuandoNaoExistePessoa() {
        Optional<Pessoa> pessoa = this.repository.findById(10L);
        this.repository.delete(pessoa.get());
    }

}