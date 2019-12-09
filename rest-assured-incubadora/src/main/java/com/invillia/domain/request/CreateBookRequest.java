package com.invillia.domain.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateBookRequest {

    @NotBlank
    private String title;

    @NotNull
    private Integer numberOfPages;

    @NotBlank
    private String isbn;

    @NotBlank
    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(final Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(final String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

}
