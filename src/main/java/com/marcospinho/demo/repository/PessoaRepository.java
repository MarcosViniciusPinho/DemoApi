package com.marcospinho.demo.repository;

import com.marcospinho.demo.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Marcos Pinho
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}