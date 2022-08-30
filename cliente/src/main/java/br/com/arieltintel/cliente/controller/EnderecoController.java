package br.com.arieltintel.cliente.controller;

import br.com.arieltintel.cliente.dto.EnderecoDto;
import br.com.arieltintel.cliente.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/endereco")
    public ResponseEntity<List<EnderecoDto>> listarEnderecos(){
        return ResponseEntity.ok(enderecoService.listarEnderecos());
    }
}
