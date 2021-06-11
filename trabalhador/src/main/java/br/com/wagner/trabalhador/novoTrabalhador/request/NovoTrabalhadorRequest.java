package br.com.wagner.trabalhador.novoTrabalhador.request;

import br.com.wagner.trabalhador.novoTrabalhador.model.Trabalhador;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovoTrabalhadorRequest {

    // atributos basicos

    @NotBlank
    private String nome;

    @Positive
    private BigDecimal rendaDiaria;

    // construtor com atributos

    @JsonCreator
    public NovoTrabalhadorRequest(String nome, BigDecimal rendaDiaria) {
        this.nome = nome;
        this.rendaDiaria = rendaDiaria;
    }

    // getters


    public String getNome() {
        return nome;
    }

    public BigDecimal getRendaDiaria() {
        return rendaDiaria;
    }

    @Override
    public String toString() {
        return "NovoTrabalhadorRequest{" +
                "nome='" + nome + '\'' +
                ", rendaDiaria=" + rendaDiaria +
                '}';
    }

    // metodo para converter a os dados da requisição em entidade

    public Trabalhador toModel() {

        return new Trabalhador(nome, rendaDiaria);
    }
}
