package br.com.leonardoferreira.present.factory;

import br.com.leonardoferreira.jbacon.JBacon;
import br.com.leonardoferreira.jbacon.annotation.JBaconTemplate;
import br.com.leonardoferreira.present.domain.request.ContactRequest;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

@Component
public class ContactRequestFactory extends JBacon<ContactRequest> {

    private final Faker faker;

    public ContactRequestFactory(Faker faker) {
        this.faker = faker;
    }

    @Override
    protected ContactRequest getDefault() {
        ContactRequest contactRequest = new ContactRequest();
        contactRequest.setEmail(faker.internet().emailAddress());
        contactRequest.setName(faker.name().fullName());
        return contactRequest;
    }

    @Override
    protected ContactRequest getEmpty() {
        return new ContactRequest();
    }

    @Override
    protected void persist(ContactRequest contactRequest) {
        throw new IllegalArgumentException();
    }

    @JBaconTemplate
    protected ContactRequest invalid() {
        return new ContactRequest();
    }

}
