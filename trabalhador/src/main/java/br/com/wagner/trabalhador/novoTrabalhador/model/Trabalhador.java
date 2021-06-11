package br.com.wagner.trabalhador.novoTrabalhador.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Trabalhador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataRegistro = LocalDateTime.now();

    // atributos basicos

    private String nome;

    private BigDecimal rendaDiaria;

    // construtor default

    @Deprecated
    public Trabalhador() {
    }

    // construtor com atributos


    public Trabalhador(String nome, BigDecimal rendaDiaria) {
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

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    // equals & hashCode


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trabalhador that = (Trabalhador) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
