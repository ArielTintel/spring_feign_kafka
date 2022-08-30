package br.com.arieltintel.cliente.listener;

import br.com.arieltintel.cliente.dto.EnderecoDto;
import br.com.arieltintel.cliente.model.Endereco;
import br.com.arieltintel.cliente.service.EnderecoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class EnderecoListener {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ModelMapper modelMapper;

    @KafkaListener(topics = "${topic.endereco-client}", groupId = "${spring.kafka.consumer.group-id}")
    public void obterEndereco(String mensagem) throws JsonProcessingException {

        log.info("#### Mensagem Endereco {}", mensagem);

        ObjectMapper objectMapper = new ObjectMapper();
        EnderecoDto enderecoDto = objectMapper.readValue(mensagem, EnderecoDto.class);

        Endereco endereco = modelMapper.map(enderecoDto, Endereco.class);

        enderecoService.salvar(endereco);
        log.info("#### Endereco salvo na base com sucesso: {}", endereco);

    }

}

