package br.com.wagner.trabalhador.novoTrabalhador.controller;

import br.com.wagner.trabalhador.novoTrabalhador.model.Trabalhador;
import br.com.wagner.trabalhador.novoTrabalhador.repository.TrabalhadorRepository;
import br.com.wagner.trabalhador.novoTrabalhador.request.NovoTrabalhadorRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/trabalhadores")
public class NovoTrabalhadorController {

    private Logger logger = LoggerFactory.getLogger(NovoTrabalhadorController.class);

    @Autowired
    private TrabalhadorRepository trabalhadorRepository;
    
    // end point / cadastrar um novo trabalhador

    @Transactional
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoTrabalhadorRequest request) {
        logger.info("...Inicializando o cadastro de um trabalhador: " + request.getNome());

        Trabalhador trabalhador = request.toModel();

        trabalhadorRepository.save(trabalhador);

        logger.info("...Trabalhador cadastrado com sucesso: " + trabalhador.getNome());
        return ResponseEntity.ok().build();
    }
}
