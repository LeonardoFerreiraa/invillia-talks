package br.com.leonardoferreira.past.mapper;

import br.com.leonardoferreira.past.domain.Contact;
import br.com.leonardoferreira.past.domain.request.ContactRequest;
import br.com.leonardoferreira.past.domain.response.ContactResponse;
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
