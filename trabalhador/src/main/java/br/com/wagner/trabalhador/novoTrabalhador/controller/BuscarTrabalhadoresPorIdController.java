package br.com.wagner.trabalhador.novoTrabalhador.controller;

import br.com.wagner.trabalhador.exceptions.ResourceNotFoundException;
import br.com.wagner.trabalhador.novoTrabalhador.model.Trabalhador;
import br.com.wagner.trabalhador.novoTrabalhador.repository.TrabalhadorRepository;
import br.com.wagner.trabalhador.novoTrabalhador.response.TrabalhadorDetalhesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("trabalhadores")
public class BuscarTrabalhadoresPorIdController {

    private final Logger logger = LoggerFactory.getLogger(BuscarTrabalhadoresPorIdController.class);

    @Autowired
    private TrabalhadorRepository trabalhadorRepository;

    // end point para buscar trabalhador por id

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        logger.info("...Iniciando a busca de um trabalhador por id: " + id);

        Optional<Trabalhador> obj = trabalhadorRepository.findById(id);

        Trabalhador trabalhador = obj.orElseThrow(() -> new ResourceNotFoundException("id do trabalhador não encontrado"));

        return ResponseEntity.ok().body(new TrabalhadorDetalhesResponse(trabalhador));
    }
}
