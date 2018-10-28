package com.marcospinho.demo.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcospinho.demo.entity.Pessoa;
import com.marcospinho.demo.service.PessoaService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Marcos Pinho
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class PessoaResourceUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PessoaService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testListSucess() throws Exception {

        Mockito.when(this.service.findAll()).thenReturn(Optional.of(Arrays.asList(new Pessoa(1L, "Marcos", "Pinho", 32))));

        this.mockMvc.perform(get("/pessoas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("Marcos"))
                .andExpect(jsonPath("$[0].sobrenome").value("Pinho"))
                .andExpect(jsonPath("$[0].idade").value(32))
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    public void testListSucessSemRegistro() throws Exception {

        Mockito.when(this.service.findAll()).thenReturn(Optional.of(new ArrayList<>()));

        MockHttpServletResponse response = this.mockMvc.perform(get("/pessoas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0))
                .andReturn().getResponse();

        Assert.assertEquals("[]", response.getContentAsString());
    }

    @Test
    public void testFindSucess() throws Exception {
        Mockito.when(this.service.findById(1L)).thenReturn(Optional.of(new Pessoa(1L, "Marcos", "Pinho", 32)));

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
    public void testFindSucessSemRegistro() throws Exception {
        Mockito.when(this.service.findById(10L)).thenReturn(Optional.of(new Pessoa()));

        MockHttpServletResponse response = this.mockMvc.perform(get("/pessoas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse();

        Assert.assertEquals("null", response.getContentAsString());
    }

    @Test
    public void testCreateSucess() throws Exception {
        Pessoa pessoa = new Pessoa(null, "Sabrina", "Pinho", 25);

        Mockito.when(this.service.create(pessoa)).thenReturn(Optional.of(new Pessoa(2L, "Sabrina", "Pinho", 25)));

        MockHttpServletResponse response = this.mockMvc.perform(post("/pessoas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(pessoa)))
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
        Pessoa pessoa = new Pessoa(null, "Sabrina3", "Pinho3", 25);

        Mockito.when(this.service.update(2L, pessoa)).thenReturn(Optional.of(new Pessoa(2L, "Sabrina3", "Pinho3", 25)));

        this.mockMvc.perform(put("/pessoas/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(pessoa)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.nome").value("Sabrina3"))
                .andExpect(jsonPath("$.sobrenome").value("Pinho3"))
                .andExpect(jsonPath("$.idade").value(25));
    }

    @Test
    public void testDeleteSucess() throws Exception {
        this.mockMvc.perform(delete("/pessoas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}
