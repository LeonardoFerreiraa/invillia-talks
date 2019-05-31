package com.invillia.tcc.mapper;

import com.invillia.tcc.domain.Contact;
import com.invillia.tcc.domain.request.ContactRequest;
import com.invillia.tcc.domain.response.ContactResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    @Mappings({
            @Mapping(target = "createdAt", dateFormat = "dd/MM/yyyy HH:mm"),
            @Mapping(target = "updatedAt", dateFormat = "dd/MM/yyyy HH:mm")
    })
    ContactResponse contactToContactResponse(Contact contact);

    List<ContactResponse> contactToContactResponse(List<Contact> contacts);

    default Page<ContactResponse> contactToContactResponse(Page<Contact> contacts) {
        List<ContactResponse> contactResponses = contactToContactResponse(contacts.getContent());
        return new PageImpl<>(contactResponses, contacts.getPageable(), contacts.getTotalElements());
    }

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })
    Contact contactRequestToContact(ContactRequest contactRequest);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })
    void updateContactFromRequest(@MappingTarget Contact contact, ContactRequest contactRequest);

}
