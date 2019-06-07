package br.com.leonardoferreira.present.mapper;

import br.com.leonardoferreira.present.domain.Contact;
import br.com.leonardoferreira.present.domain.request.ContactRequest;
import br.com.leonardoferreira.present.domain.response.ContactResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    @Mappings({
            @Mapping(target = "createdAt", dateFormat = "dd/MM/yyyy HH:mm:ss"),
            @Mapping(target = "updatedAt", dateFormat = "dd/MM/yyyy HH:mm:ss")
    })
    ContactResponse contactToContactResponse(Contact contact);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })
    Contact contactRequestToContact(ContactRequest contactRequest);

}
