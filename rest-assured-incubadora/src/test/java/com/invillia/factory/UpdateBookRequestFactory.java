package com.invillia.factory;

import br.com.leonardoferreira.jbacon.JBacon;
import com.github.javafaker.Faker;
import com.invillia.domain.request.UpdateBookRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateBookRequestFactory extends JBacon<UpdateBookRequest> {

    private final Faker faker;

    public UpdateBookRequestFactory(final Faker faker) {
        this.faker = faker;
    }

    @Override
    protected UpdateBookRequest getDefault() {
        final UpdateBookRequest updateBookRequest = new UpdateBookRequest();

        updateBookRequest.setTitle(faker.book().title());
        updateBookRequest.setNumberOfPages(faker.number().numberBetween(50, 200));
        updateBookRequest.setAuthor(faker.book().author());

        return updateBookRequest;
    }

    @Override
    protected UpdateBookRequest getEmpty() {
        return new UpdateBookRequest();
    }

    @Override
    protected void persist(final UpdateBookRequest createBookRequest) {
        throw new UnsupportedOperationException();
    }

}
