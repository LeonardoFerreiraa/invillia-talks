package br.com.leonardoferreira.mapstruct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Mapper
@Component
public class StringMapper {

    @Named("maiusculo")
    public String maiusculo(String str) {
        return str.toUpperCase();
    }

}
