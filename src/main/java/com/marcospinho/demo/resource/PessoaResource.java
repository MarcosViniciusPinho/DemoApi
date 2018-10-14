package com.marcospinho.demo.resource;

import com.marcospinho.demo.entity.Pessoa;
import com.marcospinho.demo.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * @author Marcos Pinho
 */
@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaService service;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Optional<Pessoa>> create(@Valid @RequestBody Pessoa pessoa){
       Optional<Pessoa> pessoaSalvo = this.service.create(pessoa);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(pessoaSalvo.get().getId()).toUri();

        return ResponseEntity.created(uri).body(pessoaSalvo);
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Optional<Pessoa>> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Pessoa pessoa){
        return ResponseEntity.ok().body(this.service.update(id, pessoa));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Optional<List<Pessoa>>> list(){
        return ResponseEntity.ok().body(this.service.findAll());
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Optional<Pessoa>> find(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(this.service.findById(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}