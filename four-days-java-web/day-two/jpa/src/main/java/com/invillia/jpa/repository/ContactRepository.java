package com.invillia.jpa.repository;

import com.invillia.jpa.domain.Contact;
import com.invillia.jpa.projection.ContactNameProjection;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface ContactRepository extends Repository<Contact, Long> {

    @Query("from Contact")
    List<Contact> selectAllFromContact();

    List<Contact> findAll();

    Contact findByName(String name);

    @Query("select name as name from Contact")
    List<ContactNameProjection> findAllNames();

}
