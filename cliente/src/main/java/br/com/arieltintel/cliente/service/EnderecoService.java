package br.com.arieltintel.cliente.service;

import br.com.arieltintel.cliente.dto.EnderecoDto;
import br.com.arieltintel.cliente.model.Endereco;
import br.com.arieltintel.cliente.repository.EnderecoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void salvar(Endereco endereco) {
        enderecoRepository.save(endereco);
    }

    public List<EnderecoDto> listarEnderecos() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        List<EnderecoDto> enderecosDto = new ArrayList<>();
        enderecos.stream().forEach(end -> {
            EnderecoDto enderecoDto = modelMapper.map(end, EnderecoDto.class);
            enderecosDto.add(enderecoDto);
        });
        return enderecosDto;
    }

}
