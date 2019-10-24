package com.invillia.springdatajpa.repository;

import com.invillia.springdatajpa.domain.Author;
import com.invillia.springdatajpa.domain.Book;
import com.invillia.springdatajpa.domain.response.TitlesResponse;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    List<Author> findByName(String name);

    List<Author> findByNameStartingWith(String name);

    List<Author> findByCreatedAtBetween(LocalDateTime begin, LocalDateTime end);

    boolean existsByName(String name);

    boolean existsByNameIgnoreCase(String name);

    List<Author> findByBioLikeIgnoreCase(String bio);

    List<Author> findByNameOrBioLike(String name, String bio);

    Long countByName(String name);

    @Query(
            "select a from Author a " +
                    " join fetch a.books b "
    )
    List<Author> findAllCompletao();

    @Query(
            " select sum(b.numberOfPages) from Author a " +
                    " join a.books b"
    )
    Long findAllPageNumbers();

    @Query(
            value = "select avg(b.number_of_pages) " +
                    "from author a " +
                    "join book b on b.author_id = a.id",
            nativeQuery = true
    )
    List<Long> findAllAvgPageNumbers();

    @Query(
            value = " select a.name as name, b.title as title " +
                    " from Author a " +
                    " join a.books b "
    )
    List<TitlesResponse> findAllTitles();

    Page<Book> findAll(Pageable page);

}
