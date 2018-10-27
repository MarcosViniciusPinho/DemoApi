package com.marcospinho.demo.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcospinho.demo.entity.Pessoa;
import com.marcospinho.demo.service.PessoaService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Marcos Pinho
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class PessoaResourceIntTest extends IntegrationSource {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PessoaService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void init() {
        this.service.create(new Pessoa(null, "Marcos", "Pinho", 32));
    }

    @Test
    public void testListSucess() throws Exception {
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
    public void testFindSucess() throws Exception {
        this.mockMvc.perform(get("/pessoas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Marcos"))
                .andExpect(jsonPath("$.sobrenome").value("Pinho"))
                .andExpect(jsonPath("$.idade").value(32));
    }

    @Test
    public void testCreateSucess() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(post("/pessoas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new Pessoa(null, "Sabrina", "Pinho", 25))))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.nome").value("Sabrina"))
                .andExpect(jsonPath("$.sobrenome").value("Pinho"))
                .andExpect(jsonPath("$.idade").value(25)).andReturn().getResponse();

        Assert.assertTrue(response.getHeader("Location").contains("/pessoas/2"));
    }

    @Test
    public void testUpdateSucess() throws Exception {
        this.mockMvc.perform(put("/pessoas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new Pessoa(null, "Marcos5", "Pinho5", 33))))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Marcos5"))
                .andExpect(jsonPath("$.sobrenome").value("Pinho5"))
                .andExpect(jsonPath("$.idade").value(33));
    }

    @Test
    public void testDeleteSucess() throws Exception {
        this.mockMvc.perform(delete("/pessoas/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}
