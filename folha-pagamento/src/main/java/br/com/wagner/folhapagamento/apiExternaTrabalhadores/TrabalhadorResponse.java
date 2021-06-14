package br.com.wagner.folhapagamento.apiExternaTrabalhadores;

import java.math.BigDecimal;

public class TrabalhadorResponse {

    // atributos basicos

    private Long id;

    private String nome;

    private BigDecimal rendaDiaria;

    // construtor dafalt

    @Deprecated
    public TrabalhadorResponse() {
    }

    // construtor com atributos


    public TrabalhadorResponse(Long id, String nome, BigDecimal rendaDiaria) {
        this.id = id;
        this.nome = nome;
        this.rendaDiaria = rendaDiaria;
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

    // metodo

    public BigDecimal total(Integer diasTrabalhados) {
        return rendaDiaria.multiply(BigDecimal.valueOf(diasTrabalhados));
    }
}
