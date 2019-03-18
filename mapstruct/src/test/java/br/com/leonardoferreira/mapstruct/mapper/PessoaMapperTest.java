package br.com.leonardoferreira.mapstruct.mapper;

import br.com.leonardoferreira.mapstruct.model.Pessoa;
import br.com.leonardoferreira.mapstruct.model.PessoaTipo;
import br.com.leonardoferreira.mapstruct.model.response.PessoaResponse;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MapStructConfig.class)
class PessoaMapperTest {

    private final PessoaMapper pessoaMapper;

    @Autowired
    PessoaMapperTest(PessoaMapper pessoaMapper) {
        this.pessoaMapper = pessoaMapper;
    }

    @Test
    void toPessoaResponse() {
        Pessoa pessoa = new Pessoa(1L, "Leonardo", "mail@leonardoferreira.com.br", "dr", LocalDateTime.now(), PessoaTipo.PESSOA_FISICA);
        PessoaResponse response = pessoaMapper.toPessoaResponse(pessoa);

        Assertions.assertEquals(pessoa.getNome(), response.getNome());
        Assertions.assertEquals(pessoa.getEmail(), response.getMail());
        System.out.println(response);
    }

    @Test
    void testInverse() {
        PessoaResponse pessoaResponse = new PessoaResponse();

        pessoaResponse.setNome("teste1");
        pessoaResponse.setMail("teste2");
        pessoaResponse.setTitulo("teste2");
        pessoaResponse.setDataDeCriacao("26/12/2018 12:32");
        pessoaResponse.setPessoaTipo("Pessoa Fisica!!");
        pessoaResponse.setTantofaz("tantofaz");

        System.out.println(pessoaMapper.fromResponse(pessoaResponse));
    }
}
