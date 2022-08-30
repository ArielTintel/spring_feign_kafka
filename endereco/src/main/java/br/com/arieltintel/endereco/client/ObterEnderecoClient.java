package br.com.arieltintel.endereco.client;

import br.com.arieltintel.endereco.dto.BrasilApiResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "brasil-api", url = "https://brasilapi.com.br/api/cep/v1")
public interface ObterEnderecoClient {

    @GetMapping("/{cep}")
    BrasilApiResponseDto obterCep(@PathVariable("cep") String cep);

}
