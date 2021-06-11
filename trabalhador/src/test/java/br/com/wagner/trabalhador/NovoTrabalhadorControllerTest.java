package br.com.wagner.trabalhador;

import br.com.wagner.trabalhador.novoTrabalhador.repository.TrabalhadorRepository;
import br.com.wagner.trabalhador.novoTrabalhador.request.NovoTrabalhadorRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@ActiveProfiles("test")
@Transactional
public class NovoTrabalhadorControllerTest {

    @Autowired
    private TrabalhadorRepository trabalhadorRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String nome = "joao";
    private BigDecimal rendaDiaria = new BigDecimal("20.0");


    // 1 cenario de teste / caminho feliz

    @Test
    @DisplayName("deve retornar 200, cadastrando um noco trabalhador")
    public void deverRetornar200() throws Exception{

        // cenario

        NovoTrabalhadorRequest request = new NovoTrabalhadorRequest("joao", new BigDecimal(20.0));

        URI uri = new URI("/trabalhadores");

        // ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
                .andExpect(MockMvcResultMatchers.status().is(200));

        // assertivas
    }

    // 2 cenario de teste

    @Test
    @DisplayName("deve retornar 400, quando nome vinher vazio")
    public void deverRetornar400QuandoNomeVazio() throws Exception{

        // cenario

        NovoTrabalhadorRequest request = new NovoTrabalhadorRequest("", new BigDecimal(20.0));

        URI uri = new URI("/trabalhadores");

        // ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
                .andExpect(MockMvcResultMatchers.status().is(400));

        // assertivas
    }

    // 3 cenario de teste

    @Test
    @DisplayName("deve retornar 400, quando salario negativo")
    public void deverRetornar400QuandosalarioNegativo() throws Exception{

        // cenario

        NovoTrabalhadorRequest request = new NovoTrabalhadorRequest("joao", new BigDecimal(-50.0));

        URI uri = new URI("/trabalhadores");

        // ação

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON).content(toJson(request)))
                .andExpect(MockMvcResultMatchers.status().is(400));

        // assertivas
    }

    // metodo para desserializar objeto com dados da requisição

    private String toJson(NovoTrabalhadorRequest request) throws JsonProcessingException {
        return objectMapper.writeValueAsString(request);
    }

}
