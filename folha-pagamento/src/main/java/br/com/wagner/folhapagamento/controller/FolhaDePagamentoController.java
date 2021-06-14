package br.com.wagner.folhapagamento.controller;

import br.com.wagner.folhapagamento.exception.ResourceNotFoundException;
import br.com.wagner.folhapagamento.model.Pagamento;
import br.com.wagner.folhapagamento.service.FolhaDePagamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("pagamentos")
public class FolhaDePagamentoController {

    private final Logger logger = LoggerFactory.getLogger(FolhaDePagamentoController.class);

    @Autowired
    private FolhaDePagamentoService service;

    // end point para consulta

    @GetMapping(value = "/{id}/dias/{dias}")
    public ResponseEntity<?> consulta(@PathVariable("id") Long id, @PathVariable("dias") Integer dias) {
        logger.info("iniciando consulta de pagamento");

        Optional<Pagamento> obj = service.consulta(id, dias);

        Pagamento pagamento = obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));

        logger.info("Retornando com os dados da consulta......");
        return  ResponseEntity.ok().body(pagamento);


    }
}
