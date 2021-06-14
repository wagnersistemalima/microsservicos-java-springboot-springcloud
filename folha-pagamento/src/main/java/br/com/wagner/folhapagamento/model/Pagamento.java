package br.com.wagner.folhapagamento.model;

import java.math.BigDecimal;

public class Pagamento {

    // atributos basicos

    private Long id;

    private String nome;

    private BigDecimal rendaDiaria;

    private Integer diasTrabalhados;

    // construtor default

    @Deprecated
    public Pagamento() {
    }

    // construtor com argumentos

    public Pagamento(Long id, String nome, BigDecimal rendaDiaria, Integer diasTrabalhados) {
        this.id = id;
        this.nome = nome;
        this.rendaDiaria = rendaDiaria;
        this.diasTrabalhados = diasTrabalhados;
    }

    // getters

    public String getNome() {
        return nome;
    }

    public BigDecimal getRendaDiaria() {
        return rendaDiaria;
    }


    public Long getId() {
        return id;
    }

    public Integer getDiasTrabalhados() {
        return diasTrabalhados;
    }


    // metodo

    public BigDecimal getTotal() {
        return rendaDiaria.multiply(BigDecimal.valueOf(diasTrabalhados));
    }
}
