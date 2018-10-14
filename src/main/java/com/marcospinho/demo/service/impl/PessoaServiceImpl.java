package com.marcospinho.demo.service.impl;

import com.marcospinho.demo.entity.Pessoa;
import com.marcospinho.demo.repository.PessoaRepository;
import com.marcospinho.demo.service.PessoaService;
import com.marcospinho.demo.service.exception.NotEqualsRecurseException;
import com.marcospinho.demo.service.exception.RecurseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Marcos Pinho
 */
@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Pessoa> create(Pessoa pessoa) {
        pessoa.setId(null);//Para garantir se o usuário forçar um id na requisição o código ignorar.
        return Optional.of(this.repository.save(pessoa));
    }

    @Override
    public Optional<Pessoa> update(Long id, Pessoa pessoa) {
        Optional<Pessoa> pessoaEncontrada = this.valid(id);
        if(!pessoaEncontrada.get().equals(pessoa)) {
            throw new NotEqualsRecurseException("Houve um erro ao alterar um recurso", "O recurso solicitado é diferente do informado");
        }
        return Optional.of(this.repository.save(pessoa));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Pessoa>> findAll() {
        return Optional.of(this.repository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Pessoa> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public void delete(Long id) {
        Optional<Pessoa> pessoaEncontrada = this.valid(id);
        this.repository.delete(pessoaEncontrada.get());
    }

    private Optional<Pessoa> valid(Long id) {
        Optional<Pessoa> pessoa = this.findById(id);
        if(!pessoa.isPresent()) {
            throw new RecurseNotFoundException("A pessoa não foi encontrada", "Não foi encontrada uma pessoa com o id fornecido");
        }
        return pessoa;
    }
}