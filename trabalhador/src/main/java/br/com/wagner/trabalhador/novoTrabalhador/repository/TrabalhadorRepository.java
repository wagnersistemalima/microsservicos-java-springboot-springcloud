package br.com.wagner.trabalhador.novoTrabalhador.repository;

import br.com.wagner.trabalhador.novoTrabalhador.model.Trabalhador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabalhadorRepository extends JpaRepository<Trabalhador, Long> {

}
