package br.com.leonardoferreira.mapstruct.mapper;

import br.com.leonardoferreira.mapstruct.model.Carro;
import br.com.leonardoferreira.mapstruct.model.request.CarroRequest;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class CarroMapperTest {


    @Test
    void testFromRequest() {
        CarroMapper mapper = Mappers.getMapper(CarroMapper.class);
        CarroRequest carroRequest = new CarroRequest("qualquer", "tantofaz");
        Carro carro = mapper.fromRequest(carroRequest);
        System.out.println(carro);
    }
    @Test
    void testUpdateFromRequest() {
        CarroMapper mapper = Mappers.getMapper(CarroMapper.class);

        CarroRequest carroRequest = new CarroRequest("qualquer", "tantofaz");
        Carro carro = new Carro();
        carro.setId(1L);

        mapper.fromRequest(carro, carroRequest);

        System.out.println(carro);
    }
}
