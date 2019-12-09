package com.invillia.factory;

import br.com.leonardoferreira.jbacon.JBacon;
import com.github.javafaker.Faker;
import com.invillia.domain.request.CreateBookRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateBookRequestFactory extends JBacon<CreateBookRequest> {

    private final Faker faker;

    public CreateBookRequestFactory(final Faker faker) {
        this.faker = faker;
    }

    @Override
    protected CreateBookRequest getDefault() {
        final CreateBookRequest createBookRequest = new CreateBookRequest();

        createBookRequest.setTitle(faker.book().title());
        createBookRequest.setNumberOfPages(faker.number().numberBetween(50, 200));
        createBookRequest.setIsbn(faker.idNumber().valid());
        createBookRequest.setAuthor(faker.book().author());

        return createBookRequest;
    }

    @Override
    protected CreateBookRequest getEmpty() {
        return new CreateBookRequest();
    }

    @Override
    protected void persist(final CreateBookRequest createBookRequest) {
        throw new UnsupportedOperationException();
    }
}
