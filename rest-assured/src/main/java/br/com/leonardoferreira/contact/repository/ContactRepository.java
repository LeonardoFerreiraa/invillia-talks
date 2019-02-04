package br.com.leonardoferreira.contact.repository;

import br.com.leonardoferreira.contact.domain.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
}