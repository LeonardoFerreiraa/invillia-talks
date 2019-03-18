package br.com.leonardoferreira.mapstruct.mapper;

import br.com.leonardoferreira.mapstruct.model.Pessoa;
import br.com.leonardoferreira.mapstruct.model.PessoaTipo;
import br.com.leonardoferreira.mapstruct.model.response.PessoaResponse;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = StringMapper.class, componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PessoaMapper {

    @Mappings({
            @Mapping(target = "mail", source = "email"),
            @Mapping(target = "tantofaz", ignore = true),
            @Mapping(target = "dataDeCriacao", dateFormat = "dd/MM/yyyy HH:mm"),
            @Mapping(target = "titulo", qualifiedByName = "maiusculo")
    })
    PessoaResponse toPessoaResponse(Pessoa pessoa);

    List<PessoaResponse> toPessoaResponse(List<Pessoa> pessoa);

    @InheritInverseConfiguration
    Pessoa fromResponse(PessoaResponse pessoaResponse);

    default String pessoaTipoTradutor(PessoaTipo pessoaTipo) {
        switch (pessoaTipo) {
            case PESSOA_FISICA:
                return "Pessoa Fisica!!";
            case PESSOA_JURIDICA:
                return "Pessoa Juridica!!";
        }

        return null;
    }

    default PessoaTipo fromString(String s) {
        switch (s) {
            case "Pessoa Fisica!!":
                return PessoaTipo.PESSOA_FISICA;
            case "Pessoa Juridica!!":
                return PessoaTipo.PESSOA_JURIDICA;
        }

        return null;
    }

}
