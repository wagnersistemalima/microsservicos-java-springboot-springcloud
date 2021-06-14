package br.com.wagner.folhapagamento.service;

import br.com.wagner.folhapagamento.apiExternaTrabalhadores.ApiFeingTrabalhadoresClient;
import br.com.wagner.folhapagamento.apiExternaTrabalhadores.TrabalhadorResponse;
import br.com.wagner.folhapagamento.exception.ResourceNotFoundException;
import br.com.wagner.folhapagamento.model.Pagamento;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FolhaDePagamentoService {

    @Autowired
    private ApiFeingTrabalhadoresClient apiFeingTrabalhadoresClient;

    public Pagamento consulta(Long id, Integer dias) {

        TrabalhadorResponse response = apiFeingTrabalhadoresClient.findById(id).getBody();
        
        Pagamento pagamento = new Pagamento(response.getId(), response.getNome(), response.getRendaDiaria(), dias);
        return  pagamento;

    }


}
