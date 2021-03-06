package com.estoques.demo;

import com.estoques.controllers.TipoProdutoController;
import com.estoques.models.TipoProduto;
import com.estoques.repositories.TipoProdutoRepository;
import com.estoques.services.TipoProdutoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class IntegrationTest extends IntegrationTestConfig {

    @Autowired
    private TipoProdutoController tipoProdutoController;
    @Autowired
    private TipoProdutoRepository tipoProdutoRepository;
    @Autowired
    private TipoProdutoService tipoProdutoService;

    private String retornaNomeAleatorio(){
        Random random = new Random();
        int numeroInteiroAleatorio_0_a_1000 = random.nextInt(1000);
        return "TesteIntegracao" + numeroInteiroAleatorio_0_a_1000;
    }

    @Test
    void shouldCreateaNewTipoProduto() throws Exception {

        TipoProduto tipo = TipoProduto.builder().name(retornaNomeAleatorio()).build();

        String content = objectMapper.writeValueAsString(tipo);

        mockMvc.perform(
                post("/tipoProduto")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    @Test
    void shouldGetAllTipoProdutos() throws Exception {
        String url = "/tipoProduto";
        mockMvc.perform(
                MockMvcRequestBuilders.get(url)
        ).andExpect(status().isOk());
    }

}
