package com.invillia;

import com.invillia.dao.AuthorDAO;
import com.invillia.domain.Author;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {

    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("incubaria");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("-------------------------------------------------------");
        final AuthorDAO authorDAO = new AuthorDAO(entityManager);

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

    }


}
