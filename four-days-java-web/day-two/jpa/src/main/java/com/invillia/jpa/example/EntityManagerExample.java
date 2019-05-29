package com.invillia.jpa.example;

import com.invillia.jpa.domain.Contact;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EntityManagerExample implements CommandLineRunner {

    private final EntityManager entityManager;

    public EntityManagerExample(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void run(String... args) throws Exception {
        TypedQuery<Contact> query = entityManager.createQuery("from Contact", Contact.class);
        List<Contact> resultList = query.getResultList();
        for (Contact contact : resultList) {
//            System.out.println(contact);
        }
    }

}

/*
var args -> bla("a", "b", "c");
public void bla(String... xablau) {
}
*/
