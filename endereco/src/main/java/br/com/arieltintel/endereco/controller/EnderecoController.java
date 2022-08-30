package br.com.arieltintel.endereco.controller;

import br.com.arieltintel.endereco.dto.EnderecoRequestDto;
import br.com.arieltintel.endereco.client.ObterEnderecoClient;
import br.com.arieltintel.endereco.dto.BrasilApiResponseDto;
import br.com.arieltintel.endereco.dto.EnderecoResponseDto;
import br.com.arieltintel.endereco.service.EnderecoSenderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("enderecos")
public class EnderecoController {

    private final ObterEnderecoClient obterEnderecoClient;

    @Autowired
    private EnderecoSenderService enderecoSenderService;

    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoResponseDto> obterEnderecoPorCep(@PathVariable("cep") String cep){
        BrasilApiResponseDto brasilApiResponseDto = obterEnderecoClient.obterCep(cep);
        return ResponseEntity.ok(brasilApiResponseDto.to());
    }

    @PostMapping
    public ResponseEntity<EnderecoResponseDto> enviarEndereco(@RequestBody EnderecoRequestDto enderecoRequestDto) throws JsonProcessingException {

        log.info("#### Dados recebidos pelo cliente: {}", enderecoRequestDto);

        EnderecoResponseDto enderecoResponseDto = obterEnderecoClient.obterCep(enderecoRequestDto.getCep()).to();
        enderecoResponseDto.setNumeroCasa(enderecoRequestDto.getNumeroCasa());
        enderecoResponseDto.setComplemento(enderecoRequestDto.getComplemento());

        //Converte o Objeto EnderecoResponseDTO em uma mensagem String
        ObjectMapper objectMapper = new ObjectMapper();
        String mensagem = objectMapper.writeValueAsString(enderecoResponseDto);

        enderecoSenderService.sendMessage(mensagem);
        log.info("#### Endereço retornado pela API de CEP: {}", enderecoResponseDto);

        return ResponseEntity.ok(enderecoResponseDto);
    }

}
