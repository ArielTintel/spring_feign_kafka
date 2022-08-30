package br.com.arieltintel.endereco.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrasilApiResponseDto {

    private String cep;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String service;

    public EnderecoResponseDto to(){
        return EnderecoResponseDto.builder()
                .cep(this.getCep())
                .estado(this.getState())
                .cidade(this.getCity())
                .bairro(this.getNeighborhood())
                .rua(this.getStreet())
                .build();
    }

}
