package br.com.wagner.trabalhador;

import br.com.wagner.trabalhador.novoTrabalhador.model.Trabalhador;
import br.com.wagner.trabalhador.novoTrabalhador.repository.TrabalhadorRepository;
import br.com.wagner.trabalhador.novoTrabalhador.response.BuscarTodosTrabalhadresResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@ActiveProfiles("test")
@Transactional
public class BuscarTodosTrabalhadoresControllerTest {

    @Autowired
    private TrabalhadorRepository trabalhadorRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        trabalhadorRepository.deleteAll();
    }

    // cenario de teste / caminho feliz

    @Test
    @DisplayName("deve retornar 200, com a lista de trabalhadores")
    public void deveRetornar200() throws Exception{

        // cenario

        Trabalhador trabalhador1 = new Trabalhador("nome1", new BigDecimal(20.0));
        trabalhadorRepository.save(trabalhador1);

        Trabalhador trabalhador2 = new Trabalhador("nome2", new BigDecimal(30.0));
        trabalhadorRepository.save(trabalhador2);

        Trabalhador trabalhador3 = new Trabalhador("nome3", new BigDecimal(40.0));
        trabalhadorRepository.save(trabalhador3);

        BuscarTodosTrabalhadresResponse response1 = new BuscarTodosTrabalhadresResponse(trabalhador1);
        BuscarTodosTrabalhadresResponse response2 = new BuscarTodosTrabalhadresResponse(trabalhador2);
        BuscarTodosTrabalhadresResponse response3 = new BuscarTodosTrabalhadresResponse(trabalhador3);

        List<BuscarTodosTrabalhadresResponse> lista = new ArrayList<>();
        lista.add(response1);
        lista.add(response2);
        lista.add(response3);

        URI uri = new URI("/trabalhadores");

        //ação

        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().json(toJson(lista)));

        //assertivas

        Assertions.assertEquals(3, lista.size());

    }

    // cenario de teste / caminho feliz

    @Test
    @DisplayName("deve retornar 200, com a lista vazia, caso nao tenha trabalhadores cadastrados")
    public void deveRetornar200ListaVazia() throws Exception{

        // cenario

        List<BuscarTodosTrabalhadresResponse> lista = new ArrayList<>();

        URI uri = new URI("/trabalhadores");

        //ação


        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().json(toJson(lista)));

        //assertivas

        Assertions.assertEquals(0, lista.size());

    }

    // metodo para desserializar o objeto de resposta

    private String toJson(List<BuscarTodosTrabalhadresResponse> response) throws JsonProcessingException {
        return objectMapper.writeValueAsString(response);
    }
}
