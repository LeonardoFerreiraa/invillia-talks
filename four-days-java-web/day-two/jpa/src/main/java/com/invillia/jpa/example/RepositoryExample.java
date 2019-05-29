package com.invillia.jpa.example;

import com.invillia.jpa.domain.Contact;
import com.invillia.jpa.repository.ContactRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RepositoryExample implements CommandLineRunner {

    private final ContactRepository contactRepository;

    public RepositoryExample(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        projection();
    }

    private void projection() {
        contactRepository.findAllNames()
                .forEach(project -> System.out.println(project.getName()));

        contactRepository.findAll()
                .stream()
                .map(Contact::getName)
                .collect(Collectors.toList());
    }

    private void methodNameQuery() {
        Contact fil = contactRepository.findByName("fil");
        System.out.println(fil);
    }

    private void findAllQueryName() {
        contactRepository.findAll()
                .forEach(c -> System.out.println(c.getName()));
    }

    private void hqlExample() {
        List<Contact> contacts = contactRepository.selectAllFromContact();
        contacts.forEach(System.out::println);
    }

}
