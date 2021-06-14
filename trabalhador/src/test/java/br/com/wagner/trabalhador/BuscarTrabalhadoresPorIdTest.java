package br.com.wagner.trabalhador;

import br.com.wagner.trabalhador.novoTrabalhador.model.Trabalhador;
import br.com.wagner.trabalhador.novoTrabalhador.repository.TrabalhadorRepository;
import br.com.wagner.trabalhador.novoTrabalhador.response.TrabalhadorDetalhesResponse;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@ActiveProfiles("test")
@Transactional
public class BuscarTrabalhadoresPorIdTest {

    @Autowired
    private TrabalhadorRepository trabalhadorRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // 1 cenario de teste / caminho feliz

    @Test
    @DisplayName("deve retornar 200, com os detalhes do trabalhador")
    public void deveRetornar200() throws Exception {

        // cenario

        Trabalhador trabalhador1 = new Trabalhador("Pedro", new BigDecimal(1000.0));
        trabalhadorRepository.save(trabalhador1);

        URI uri = UriComponentsBuilder.fromUriString("/trabalhadores/{id}").buildAndExpand(trabalhador1.getId()).toUri();

        // ação

        TrabalhadorDetalhesResponse response = new TrabalhadorDetalhesResponse(trabalhador1);

        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(toJson(response)))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    // 2 cenario de teste

    @Test
    @DisplayName("deve retornar 404, quando não encontrar o id do trabalhador informado")
    public void deveRetornar404() throws Exception {

        // cenario

        Trabalhador trabalhador1 = new Trabalhador("Pedro", new BigDecimal(1000.0));
        trabalhadorRepository.save(trabalhador1);

        URI uri = UriComponentsBuilder.fromUriString("/trabalhadores/{id}").buildAndExpand(5).toUri();

        // ação

        TrabalhadorDetalhesResponse response = new TrabalhadorDetalhesResponse(trabalhador1);

        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(404));

    }

    // metodo para desserializar objeto de ersposta

    private String toJson(TrabalhadorDetalhesResponse response) throws JsonProcessingException {
        return  objectMapper.writeValueAsString(response);
    }
}
