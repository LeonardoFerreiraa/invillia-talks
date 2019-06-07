package br.com.leonardoferreira.past.service;

import br.com.leonardoferreira.past.domain.request.ContactRequest;
import br.com.leonardoferreira.past.domain.response.ContactResponse;
import br.com.leonardoferreira.past.exception.ResourceNotFoundException;
import br.com.leonardoferreira.past.mapper.ContactMapper;
import br.com.leonardoferreira.past.repository.ContactRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    private final ContactMapper contactMapper;

    public ContactService(
            ContactRepository contactRepository,
            ContactMapper contactMapper
    ) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    @Transactional(readOnly = true)
    public ContactResponse findById(Long id) {
        return contactRepository.findById(id)
                .map(contactMapper::contactToContactResponse)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional
    public Long create(ContactRequest contactRequest) {
        var contact = contactMapper.contactRequestToContact(contactRequest);
        contactRepository.save(contact);

        return contact.getId();
    }


}
