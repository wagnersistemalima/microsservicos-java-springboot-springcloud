package br.com.wagner.trabalhador.novoTrabalhador.controller;

import br.com.wagner.trabalhador.novoTrabalhador.model.Trabalhador;
import br.com.wagner.trabalhador.novoTrabalhador.repository.TrabalhadorRepository;
import br.com.wagner.trabalhador.novoTrabalhador.response.BuscarTodosTrabalhadresResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trabalhadores")
public class BuscarTodosTrabalhadoresController {

    private final Logger logger = LoggerFactory.getLogger(BuscarTodosTrabalhadoresController.class);

    @Autowired
    TrabalhadorRepository trabalhadorRepository;

    // end point buscar todos os trabalhadores

    @Transactional
    @GetMapping
    public ResponseEntity<?> findAll() {
        logger.info("...Iniciando a busca de todos os trabalhadores cadastrados");

        List<Trabalhador> lista = trabalhadorRepository.findAll();

        logger.info("...Devolvendo consulta de buscar todos trabalhadores com sucesso");
        return ResponseEntity.ok().body(lista.stream().map(trabalhador -> new BuscarTodosTrabalhadresResponse(trabalhador)));
    }
}
