package com.invillia.dao;

import com.invillia.domain.Book;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class BookDAO {

    private final EntityManager entityManager;

    public BookDAO(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void insert(final Book book) {
        final EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(book);
        transaction.commit();
    }

    public List<Book> findAll() {
        final TypedQuery<Book> query = entityManager.createQuery(
                "SELECT b FROM Book b JOIN FETCH b.author",
                Book.class
        );

        return query.getResultList();
    }

    public Book findById(final Long id) {
        return entityManager.find(Book.class, id);
    }

    public void update(final Book book) {
        final EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.merge(book);
        transaction.commit();
    }

    public void coolThing() {
        final EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        final Book book = findById(1L);
        book.setTitle("senhor dos xablaus pt. 2");
        transaction.commit();
    }

    public void deleteById(final long id) {
        final EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        final Book book = findById(id);
        entityManager.remove(book);
        transaction.commit();
    }

    public List<Book> findAllByAuthorId(final Long authorId) {
        final TypedQuery<Book> query = entityManager.createQuery(
                "SELECT b FROM Book b " +
                        " join fetch b.author a " +
                        " where a.id = :authorId",
                Book.class
        );

        query.setParameter("authorId", authorId);

        return query.getResultList();
    }
}
