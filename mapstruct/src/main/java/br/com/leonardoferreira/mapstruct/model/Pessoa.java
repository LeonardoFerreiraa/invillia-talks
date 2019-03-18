package br.com.leonardoferreira.mapstruct.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

    private Long id;

    private String nome;

    private String email;

    private String titulo;

    private LocalDateTime dataDeCriacao;

    private PessoaTipo pessoaTipo;

}
