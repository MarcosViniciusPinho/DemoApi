package com.marcospinho.demo.unit;

import com.marcospinho.demo.entity.Pessoa;
import com.marcospinho.demo.repository.PessoaRepository;
import com.marcospinho.demo.service.exception.RecurseNotFoundException;
import com.marcospinho.demo.service.impl.PessoaServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PessoaServiceImplTest {

    @InjectMocks
    private PessoaServiceImpl service;

    @Mock
    private PessoaRepository repository;

    @Test
    public void testCreateSucess() {
        Pessoa pessoaExecucao = new Pessoa(null, "Marcos", "Pinho", 32);
        Pessoa pessoaEsperada = new Pessoa(1L, "Marcos", "Pinho", 32);
        Mockito.when(this.repository.save(pessoaExecucao)).thenReturn(pessoaEsperada);
        Optional pessoaSalva = this.service.create(pessoaExecucao);
        Assert.assertEquals(pessoaEsperada, pessoaSalva.get());
    }

    @Test(expected = NullPointerException.class)
    public void testCreateComException() {
        Pessoa pessoaExecucao = new Pessoa(null, "Marcos", "Pinho", 32);
        Pessoa pessoaEsperada = new Pessoa(1L, "Marcos", "Pinho", 32);
        Optional pessoaSalva = this.service.create(pessoaExecucao);
        Assert.assertEquals(pessoaEsperada, pessoaSalva.get());
    }

    @Test
    public void testUpdateSucess() {
        Pessoa pessoaExecucao = new Pessoa(1L, "Marcos2", "Pinho2", 33);
        Optional<Pessoa> pessoaEsperada = Optional.of(new Pessoa(1L, "Marcos2", "Pinho2", 33));
        Mockito.when(this.repository.findById(pessoaExecucao.getId())).thenReturn(pessoaEsperada);
        Mockito.when(this.repository.save(pessoaExecucao)).thenReturn(pessoaEsperada.get());
        Optional pessoaSalva = this.service.update(pessoaExecucao.getId(), pessoaExecucao);
        Assert.assertEquals(pessoaEsperada, pessoaSalva);
    }

    @Test(expected = RecurseNotFoundException.class)
    public void testUpdateComException() {
        Pessoa pessoaExecucao = new Pessoa(1L, "Marcos2", "Pinho2", 33);
        Optional<Pessoa> pessoaEsperada = Optional.of(new Pessoa(1L, "Marcos2", "Pinho2", 33));
        Mockito.when(this.repository.save(pessoaExecucao)).thenReturn(pessoaEsperada.get());
        Optional pessoaSalva = this.service.update(pessoaExecucao.getId(), pessoaExecucao);
        Assert.assertEquals(pessoaEsperada, pessoaSalva);
    }

    @Test
    public void testFindAllSucess() {
        Optional<List<Pessoa>> listaEsperada = Optional.of(Arrays.asList(new Pessoa(), new Pessoa(), new Pessoa()));
        Mockito.when(this.repository.findAll()).thenReturn(Arrays.asList(new Pessoa(), new Pessoa(), new Pessoa()));
        Optional<List<Pessoa>> listaRecebida = this.service.findAll();
        Assert.assertEquals(listaEsperada, listaRecebida);
    }

    @Test
    public void testFindAllNaoAchouRegistro() {
        Optional<List<Pessoa>> listaRecebida = this.service.findAll();
        Assert.assertTrue(listaRecebida.get().isEmpty());
    }

    @Test
    public void testFindByIdSucess() {
        Optional<Pessoa> pessoaEsperada = Optional.of(new Pessoa(1L, "Marcos2", "Pinho2", 33));
        Mockito.when(this.repository.findById(1L)).thenReturn(pessoaEsperada);
        Optional pessoaSalva = this.service.findById(1L);
        Assert.assertEquals(pessoaEsperada, pessoaSalva);
    }

    @Test
    public void testFindByIdNaoAchouRegistro() {
        Optional pessoaSalva = this.service.findById(1L);
        Assert.assertFalse(pessoaSalva.isPresent());
    }

    @Test
    public void testDeleteSucess() {
        Optional<Pessoa> pessoaEsperada = Optional.of(new Pessoa(1L, "Marcos2", "Pinho2", 33));
        Mockito.when(this.repository.findById(1L)).thenReturn(pessoaEsperada);
        this.service.delete(1L);
    }

    @Test(expected = RecurseNotFoundException.class)
    public void testDeleteComException() {
        this.service.delete(1L);
    }
}
