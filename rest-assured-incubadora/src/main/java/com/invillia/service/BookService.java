package com.invillia.service;

import com.invillia.domain.Book;
import com.invillia.domain.request.BookRequest;
import com.invillia.domain.response.BookResponse;
import com.invillia.exception.ResourceNotFoundException;
import com.invillia.mapper.BookMapper;
import com.invillia.repository.BookRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    public BookService(final BookRepository bookRepository,
                       final BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Transactional(readOnly = true)
    public List<BookResponse> findAll() {
        final List<Book> books = bookRepository.findAll();
        return bookMapper.bookToBookResponse(books);
    }

    @Transactional(readOnly = true)
    public BookResponse findById(final Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::bookToBookResponse)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional
    public Long create(final BookRequest bookRequest) {
        Book book = bookMapper.bookRequestToBook(bookRequest);
        bookRepository.save(book);

        return book.getId();
    }

    @Transactional
    public void update(final Long id, final BookRequest bookRequest) {
        final Book book = bookRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        bookMapper.updateBookByBookRequest(book, bookRequest);

        bookRepository.save(book);
    }

    @Transactional
    public void delete(final Long id) {
        final Book book = bookRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        bookRepository.delete(book);
    }

}
