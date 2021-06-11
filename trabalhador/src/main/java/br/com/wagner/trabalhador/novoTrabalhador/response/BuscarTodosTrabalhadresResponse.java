package br.com.wagner.trabalhador.novoTrabalhador.response;

import br.com.wagner.trabalhador.novoTrabalhador.model.Trabalhador;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.math.BigDecimal;

public class BuscarTodosTrabalhadresResponse {

    // atributos basicos

    private Long id;

    private String nome;

    private BigDecimal rendaDiaria;

    // construtor personalizado recebendo

    @JsonCreator
    public BuscarTodosTrabalhadresResponse(Trabalhador entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.rendaDiaria = entity.getRendaDiaria();
    }

    // getters


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getRendaDiaria() {
        return rendaDiaria;
    }
}
