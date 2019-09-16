package br.com.leonardoferreira.contact.factory;

import br.com.leonardoferreira.contact.repository.ContactRepository;
import br.com.leonardoferreira.jbacon.annotation.JBaconTemplate;
import com.github.javafaker.Faker;

import br.com.leonardoferreira.contact.domain.Contact;
import br.com.leonardoferreira.jbacon.JBacon;
import org.springframework.stereotype.Component;

@Component
public class ContactFactory extends JBacon<Contact> {

    private final ContactRepository contactRepository;

    private final Faker faker;

    public ContactFactory(final ContactRepository contactRepository,
                          final Faker faker) {
        this.contactRepository = contactRepository;
        this.faker = faker;
    }

    @Override
    protected Contact getDefault() {
        Contact contact = new Contact();

        contact.setName(faker.gameOfThrones().character());
        contact.setEmail(faker.internet().emailAddress());

        return contact;
    }

    @Override
    protected Contact getEmpty() {
        return new Contact();
    }

    @Override
    protected void persist(Contact contact) {
        contactRepository.save(contact);
    }

    @JBaconTemplate
    protected Contact contactInvalid() {
        Contact contact = new Contact();
        contact.setName("Leonardo Ferreira");
        contact.setEmail(null);

        return contact;
    }
}
