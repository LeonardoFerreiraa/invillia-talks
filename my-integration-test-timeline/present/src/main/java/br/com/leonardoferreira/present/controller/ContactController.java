package br.com.leonardoferreira.present.controller;

import br.com.leonardoferreira.present.domain.request.ContactRequest;
import br.com.leonardoferreira.present.domain.response.ContactResponse;
import br.com.leonardoferreira.present.service.ContactService;
import javax.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/{id}")
    public ContactResponse findById(@PathVariable Long id) {
        return contactService.findById(id);
    }

    @PostMapping
    public HttpEntity<?> create(@Valid @RequestBody ContactRequest contactRequest) {
        var id = contactService.create(contactRequest);

        var location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/contacts/{id}")
                .build(id);

        return ResponseEntity.created(location).build();
    }

}
