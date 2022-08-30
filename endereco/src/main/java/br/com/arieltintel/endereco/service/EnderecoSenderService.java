package br.com.arieltintel.endereco.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class EnderecoSenderService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topic.endereco-client}")
    private String topicEnderecoCliente;

    public void sendMessage(String message){
        kafkaTemplate.send(topicEnderecoCliente, message);
        log.info("#### Mensagem Enviada: {}", message);
    }
}
