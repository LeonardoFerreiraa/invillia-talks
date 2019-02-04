package br.com.leonardoferreira.contact.factory;

import br.com.leonardoferreira.contact.domain.Contact;
import br.com.leonardoferreira.contact.domain.request.ContactRequest;
import br.com.leonardoferreira.jbacon.JBacon;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactRequestFactory extends JBacon<ContactRequest> {

    @Autowired
    private Faker faker;

    @Override
    protected ContactRequest getDefault() {
        ContactRequest contactRequest = new ContactRequest();

        contactRequest.setName(faker.gameOfThrones().character());
        contactRequest.setEmail(faker.internet().emailAddress());

        return contactRequest;
    }

    @Override
    protected ContactRequest getEmpty() {
        return new ContactRequest();
    }

    @Override
    protected void persist(ContactRequest contactRequest) {
        throw new UnsupportedOperationException();
    }
}
