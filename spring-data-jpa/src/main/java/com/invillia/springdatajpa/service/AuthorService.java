package com.invillia.springdatajpa.service;

import com.invillia.springdatajpa.domain.Author;
import com.invillia.springdatajpa.domain.Book;
import com.invillia.springdatajpa.repository.AuthorRepository;
import com.invillia.springdatajpa.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    public AuthorService(final AuthorRepository authorRepository,
                         final BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void insertAuthorAndBooks() {
        final Author author = new Author("Rafael", "aquela lá");
        authorRepository.save(author);

        for (int i = 0; i < 10; i++) {
            final Book book = new Book(
                    "livro " + i,
                    i * 10,
                    "123321123" + i,
                    author
            );
            bookRepository.save(book);
        }
    }

}
