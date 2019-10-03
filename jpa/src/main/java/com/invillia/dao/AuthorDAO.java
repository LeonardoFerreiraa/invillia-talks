package com.invillia.dao;

import com.invillia.domain.Author;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

// data access object
public class AuthorDAO {

    private final EntityManager entityManager;

    public AuthorDAO(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void insert(final Author author) {
        final EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(author);
        transaction.commit();
    }

    public List<Author> findAll() {
        final TypedQuery<Author> author = entityManager.createQuery(
                "from Author",
                Author.class
        );
        return author.getResultList();
    }

    public Author findById(final Long id) {
        return entityManager.find(Author.class, id);
    }

    public void update(final Author author) {
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(author);
        transaction.commit();
    }

    public void deleteFromId(final Long id) {
        final EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        final Author author = findById(id);
        entityManager.remove(author);
        transaction.commit();
    }
}
