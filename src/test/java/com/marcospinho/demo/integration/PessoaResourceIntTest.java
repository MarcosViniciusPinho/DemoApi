package com.marcospinho.demo.integration;

import com.marcospinho.demo.entity.Pessoa;
import com.marcospinho.demo.service.PessoaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Marcos Pinho
 */
@RunWith(SpringRunner.class)
public class PessoaResourceIntTest extends IntegrationSource{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PessoaService service;

    @Test
    public void testFindAllSucess() throws Exception{
        Optional<Pessoa> pessoa = this.service.create(new Pessoa(null, "Marcos", "Pinho", 32));

        this.mockMvc.perform(get("/pessoas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(pessoa.get().getId()))
                .andExpect(jsonPath("$[0].nome").value(pessoa.get().getNome()))
                .andExpect(jsonPath("$[0].sobrenome").value(pessoa.get().getSobrenome()))
                .andExpect(jsonPath("$[0].idade").value(pessoa.get().getIdade()));
    }

}
