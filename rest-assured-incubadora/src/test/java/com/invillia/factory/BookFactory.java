package com.invillia.factory;

import br.com.leonardoferreira.jbacon.JBacon;
import com.github.javafaker.Faker;
import com.invillia.domain.Book;
import com.invillia.repository.BookRepository;
import org.springframework.stereotype.Component;

@Component
public class BookFactory extends JBacon<Book> {

    private final BookRepository bookRepository;

    private final Faker faker;

    public BookFactory(final BookRepository bookRepository,
                       final Faker faker) {
        this.bookRepository = bookRepository;
        this.faker = faker;
    }

    @Override
    protected Book getDefault() {
        final Book book = new Book();

        book.setTitle(faker.book().title());
        book.setNumberOfPages(faker.number().numberBetween(50, 200));
        book.setIsbn(faker.idNumber().valid());
        book.setAuthor(faker.book().author());

        return book;
    }

    @Override
    protected Book getEmpty() {
        return new Book();
    }

    @Override
    protected void persist(final Book book) {
        bookRepository.save(book);
    }
}
