package br.com.wagner.trabalhador.novoTrabalhador.controller;

import br.com.wagner.trabalhador.exceptions.ResourceNotFoundException;
import br.com.wagner.trabalhador.novoTrabalhador.model.Trabalhador;
import br.com.wagner.trabalhador.novoTrabalhador.repository.TrabalhadorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/trabalhadores")
public class DeleteTrabalhadorController {

    private Logger logger = LoggerFactory.getLogger(DeleteTrabalhadorController.class);

    @Autowired
    private TrabalhadorRepository trabalhadorRepository;

    // end point para deletar um ttrabalhador

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete( @PathVariable("id") Long id) {
        logger.info("...Iniciando a deleção de um trabalhador");


        Optional<Trabalhador> possivelTrabalhador = trabalhadorRepository.findById(id);

        Trabalhador trabalhador = possivelTrabalhador.orElseThrow(() -> new ResourceNotFoundException("id trabalhador não encontrado"));

        trabalhadorRepository.delete(trabalhador);

        logger.info("Trabalhador deletado com sucesso: " + trabalhador.getId());
        return ResponseEntity.noContent().build();

    }
}
