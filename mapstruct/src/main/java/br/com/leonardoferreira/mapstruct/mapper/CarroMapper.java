package br.com.leonardoferreira.mapstruct.mapper;

import br.com.leonardoferreira.mapstruct.model.Carro;
import br.com.leonardoferreira.mapstruct.model.request.CarroRequest;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface CarroMapper {

    @Mapping(target = "id", ignore = true)
    Carro fromRequest(CarroRequest request);

    @InheritConfiguration
    void fromRequest(@MappingTarget Carro carro, CarroRequest request);

}
