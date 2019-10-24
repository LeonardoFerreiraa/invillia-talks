package com.invillia.springdatajpa;

import com.invillia.springdatajpa.domain.Author;
import com.invillia.springdatajpa.domain.Book;
import com.invillia.springdatajpa.domain.response.TitlesResponse;
import com.invillia.springdatajpa.repository.AuthorRepository;
import com.invillia.springdatajpa.repository.BookRepository;
import com.invillia.springdatajpa.service.AuthorService;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final AuthorService authorService;

    public MyRunner(final BookRepository bookRepository,
                    final AuthorRepository authorRepository,
                    final AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.authorService = authorService;
    }

    @Override
    public void run(final String... args) throws Exception {
        final PageRequest pageable = PageRequest.of(1, 10);
        final Page<Book> page = bookRepository.findAll(pageable);
        System.out.println(page.getContent());
    }

    /* cadastrar mil livros
        final Author author = authorRepository.findById(7L)
                .orElseThrow(IllegalArgumentException::new);

        for (int i = 0; i < 1000; i++) {
            bookRepository.save(
              new Book(
                      "book " + i,
                      (i + 1) * 10,
                      "123123" + i,
                      author
              )
            );
        }
         */

// PROJECTION \/
//        final List<TitlesResponse> allTitles = authorRepository.findAllTitles();
//        for (final TitlesResponse title : allTitles) {
//            System.out.println(title.getName() + " " + title.getTitle());
//        }

// TRANSACTIONAL \/
//        authorService.insertAuthorAndBooks();

// CRUD \/
//        final Author author = new Author("Rafael segundo", "outro grande cozinheiro de livros");
//        authorRepository.save(author);

//        authorRepository.findById(1L)
//                .ifPresent(author -> {
//                    author.setName("Rafael Primeiro");
//                    authorRepository.save(author);
//                });
//        authorRepository.deleteById(1L);
//
//        authorRepository.findById(2L)
//                .ifPresent(authorRepository::delete);
//    }

// CONSULTAS \/
//        System.out.println(authorRepository.findAll());
//        System.out.println(authorRepository.findById(2L));
//        System.out.println(authorRepository.findByNameStartingWith("Xa"));
//        System.out.println(authorRepository.findByCreatedAtBetween(
//                LocalDateTime.of(2019, 10, 23, 0, 0),
//                LocalDateTime.of(2019, 10, 23, 23, 59)
//        ));
//        System.out.println(authorRepository.existsByNameIgnoreCase("rafael"));
//        System.out.println(authorRepository.findByBioLikeIgnoreCase("%culinaria%"));
//        System.out.println(authorRepository.findAllPageNumbers());
//        System.out.println(authorRepository.countByName("Rafael"));
//        System.out.println(authorRepository.findAllAvgPageNumbers());

}
