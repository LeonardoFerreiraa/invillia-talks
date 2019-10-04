package com.invillia;

import com.invillia.dao.AuthorDAO;
import com.invillia.dao.BookDAO;
import com.invillia.domain.Author;
import com.invillia.domain.Book;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Application {

    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("incubaria");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("-------------------------------------------------------");
        final AuthorDAO authorDAO = new AuthorDAO(entityManager);
        final BookDAO bookDAO = new BookDAO(entityManager);

//        authorDAO.insert(new Author(
//                "rafael",
//                "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum"
//        ));
//        System.out.println(authorDAO.findAll());
//        final Author author = authorDAO.findById(1L);
//        System.out.println(author);
//
//        author.setName("rafael 3");
//        author.setBio("piu");
//
//        authorDAO.update(author);
//
//        System.out.println(authorDAO.findById(1L));
//        authorDAO.deleteFromId(1L);

//        final Author author = authorDAO.findById(6L);
//        final EntityTransaction transaction = entityManager.getTransaction();
//
//        transaction.begin();
//        for (int i = 0; i < 10; i++) {
//            entityManager.persist(new Book(
//                    "a grande historia do xablau, pt. " + i,
//                    100,
//                    "978-85-7254-021-" + i,
//                    author
//            ));
//        }
//        transaction.commit();

//        System.out.println(bookDAO.findAll());

//        final Book book = bookDAO.findById(1L);
//        book.setTitle("xablauzinho e os 7 anoes");
//
//        final Author author = book.getAuthor();
//        author.setName("ualbax");
//
//        bookDAO.update(book);
//        book.setAuthor(authorDAO.findById());

//        bookDAO.update(book);
//        System.out.println(bookDAO.findById(1L));

//        bookDAO.coolThing();

//        bookDAO.deleteById(1L);

//        authorDAO.deleteById(7L);

//        bookDAO.deleteById(3L);

//        bookDAO.findByAuthorId()

//        List<Book> books = bookDAO.findAllByAuthorId(6L);
//        for (final Book book : books) {
//            System.out.println(book);
//        }

        authorDAO.deleteById(6L);
    }


}
