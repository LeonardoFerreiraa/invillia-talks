package com.invillia.factory;

import br.com.leonardoferreira.jbacon.JBacon;
import com.github.javafaker.Faker;
import com.invillia.domain.Book;
import com.invillia.domain.request.BookRequest;
import org.springframework.stereotype.Component;

@Component
public class BookRequestFactory extends JBacon<BookRequest> {

    private final Faker faker;

    public BookRequestFactory(final Faker faker) {
        this.faker = faker;
    }

    @Override
    protected BookRequest getDefault() {
        final BookRequest bookRequest = new BookRequest();

        bookRequest.setTitle(faker.book().title());
        bookRequest.setNumberOfPages(faker.number().numberBetween(50, 200));
        bookRequest.setIsbn(faker.idNumber().valid());
        bookRequest.setAuthor(faker.book().author());

        return bookRequest;
    }

    @Override
    protected BookRequest getEmpty() {
        return new BookRequest();
    }

    @Override
    protected void persist(final BookRequest bookRequest) {
        throw new UnsupportedOperationException();
    }
}
