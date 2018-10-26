package com.marcospinho.demo.integration;

import com.marcospinho.demo.entity.Pessoa;
import com.marcospinho.demo.service.PessoaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Marcos Pinho
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class PessoaResourceIntTest extends IntegrationSource{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PessoaService service;

    @Before
    public void init() {
        this.service.create(new Pessoa(null, "Marcos", "Pinho", 32));
    }

    @Test
    public void testFindAllSucess() throws Exception{
        this.mockMvc.perform(get("/pessoas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("Marcos"))
                .andExpect(jsonPath("$[0].sobrenome").value("Pinho"))
                .andExpect(jsonPath("$[0].idade").value(32));
    }

    @Test
    public void testFindByIdSucess() throws Exception{
        this.mockMvc.perform(get("/pessoas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Marcos"))
                .andExpect(jsonPath("$.sobrenome").value("Pinho"))
                .andExpect(jsonPath("$.idade").value(32));
    }

}
