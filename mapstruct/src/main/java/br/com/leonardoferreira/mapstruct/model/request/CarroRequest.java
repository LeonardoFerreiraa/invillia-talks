package br.com.leonardoferreira.mapstruct.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarroRequest {

    private String nome;

    private String modelo;

}
