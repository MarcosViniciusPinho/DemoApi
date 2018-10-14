package com.marcospinho.demo.service;

import com.marcospinho.demo.entity.Pessoa;

import java.util.List;
import java.util.Optional;

/**
 * @author Marcos Pinho
 */
public interface PessoaService {

    /**
     * Método que cria um recurso pessoa
     * @param pessoa pessoa
     * @return Optional<Pessoa>
     */
    Optional<Pessoa> create(Pessoa pessoa);

    /**
     * Método que altera um recurso ja existente de pessoa
     * @param pessoa pessoa
     * @return Optional<Pessoa>
     */
    Optional<Pessoa> update(Long id, Pessoa pessoa);

    /**
     * Método para listar todos os recursos
     * @return Optional<List<Pessoa>>
     */
    Optional<List<Pessoa>> findAll();

    /**
     * Método que recupera um recurso
     * @param id id
     * @return Optional<Pessoa>
     */
    Optional<Pessoa> findById(Long id);

    /**
     * Método que exclui um recurso
     * @param id id
     */
    void delete(Long id);
}