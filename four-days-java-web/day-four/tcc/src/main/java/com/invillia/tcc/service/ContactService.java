package com.invillia.tcc.service;

import com.invillia.tcc.domain.Contact;
import com.invillia.tcc.domain.request.ContactRequest;
import com.invillia.tcc.domain.response.ContactResponse;
import com.invillia.tcc.exception.ResourceNotFoundException;
import com.invillia.tcc.exception.ShorterNameException;
import com.invillia.tcc.mapper.ContactMapper;
import com.invillia.tcc.repository.ContactRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<ContactResponse> findAll(Pageable pageable) {
        Page<Contact> contacts = contactRepository.findAll(pageable);
        return contactMapper.contactToContactResponse(contacts);
    }

    @Transactional(rollbackFor = Exception.class)
    public Long create(ContactRequest contactRequest) throws ShorterNameException {
        Contact contact = contactMapper.contactRequestToContact(contactRequest);
        contactRepository.save(contact);

        createShorterThanOriginal(contact);

        return contact.getId();
    }

    private void createShorterThanOriginal(Contact contact) throws ShorterNameException {
        Contact other = new Contact();
        if (contact.getName().length() < 5) {
            throw new ShorterNameException();
        }
        other.setName(contact.getName().substring(0, 5));
        other.setEmail(contact.getEmail());

        contactRepository.save(other);
    }

    @Transactional(readOnly = true)
    public ContactResponse findById(Long id) {
        return contactRepository.findById(id)
                .map(c -> {
                    c.setName(c.getName().toUpperCase());
                    return c;
                })
                .map(contactMapper::contactToContactResponse)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional
    public void update(Long id, ContactRequest contactRequest) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        contactMapper.updateContactFromRequest(contact, contactRequest);

        contactRepository.save(contact);
    }

    @Transactional
    public void delete(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        contactRepository.delete(contact);
    }
}
