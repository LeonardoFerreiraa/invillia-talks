package com.invillia.mapper;

import com.invillia.domain.Book;
import com.invillia.domain.request.CreateBookRequest;
import com.invillia.domain.request.UpdateBookRequest;
import com.invillia.domain.response.BookResponse;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public BookResponse bookToBookResponse(final Book book) {
        final BookResponse bookResponse = new BookResponse();

        bookResponse.setId(book.getId());
        bookResponse.setTitle(book.getTitle());
        bookResponse.setNumberOfPages(book.getNumberOfPages());
        bookResponse.setIsbn(book.getIsbn());
        bookResponse.setAuthor(book.getAuthor());
        bookResponse.setCreatedAt(book.getCreatedAt().format(formatter));
        bookResponse.setUpdatedAt(book.getUpdatedAt().format(formatter));

        return bookResponse;
    }

    public List<BookResponse> bookToBookResponse(final List<Book> books) {
        return books.stream()
                .map(this::bookToBookResponse)
                .collect(Collectors.toList());
    }

    public Book bookRequestToBook(final CreateBookRequest createBookRequest) {
        final Book book = new Book();

        book.setTitle(createBookRequest.getTitle());
        book.setNumberOfPages(createBookRequest.getNumberOfPages());
        book.setIsbn(createBookRequest.getIsbn());
        book.setAuthor(createBookRequest.getAuthor());

        return book;
    }

    public void updateBookByBookRequest(final Book book, final UpdateBookRequest updateBookRequest) {
        book.setTitle(updateBookRequest.getTitle());
        book.setNumberOfPages(updateBookRequest.getNumberOfPages());
        book.setAuthor(updateBookRequest.getAuthor());
    }
}
