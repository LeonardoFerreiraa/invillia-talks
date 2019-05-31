package com.invillia.tcc.controller;

import com.invillia.tcc.domain.request.ContactRequest;
import com.invillia.tcc.domain.response.ContactResponse;
import com.invillia.tcc.exception.ShorterNameException;
import com.invillia.tcc.service.ContactService;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public Page<ContactResponse> findAll(Pageable pageable) {
        return contactService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ContactResponse findById(@PathVariable Long id) {
        return contactService.findById(id);
    }

    @PostMapping
    public HttpEntity<?> create(
            @Valid @RequestBody ContactRequest contactRequest
    ) throws ShorterNameException {
        Long id = contactService.create(contactRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/contacts/{id}")
                .build(id);

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(
            @PathVariable Long id,
            @Valid @RequestBody ContactRequest contactRequest
    ) {
        contactService.update(id, contactRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(
            @PathVariable Long id
    ) {
        contactService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
