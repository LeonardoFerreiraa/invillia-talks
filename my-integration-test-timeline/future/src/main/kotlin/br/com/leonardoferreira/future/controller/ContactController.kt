package br.com.leonardoferreira.future.controller

import br.com.leonardoferreira.future.domain.Contact
import br.com.leonardoferreira.future.domain.request.ContactRequest
import br.com.leonardoferreira.future.domain.response.ContactResponse
import br.com.leonardoferreira.future.service.ContactService
import org.springframework.http.HttpEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/contacts")
class ContactController(
    private val contactService: ContactService
) {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ContactResponse =
        contactService.findById(id)

    @PostMapping
    fun create(
        @Valid @RequestBody contactRequest: ContactRequest
    ): HttpEntity<*> {
        val id = contactService.create(contactRequest)

        val location = ServletUriComponentsBuilder
            .fromCurrentContextPath()
            .path("/contacts/{id}")
            .build(id)

        return ResponseEntity.created(location).build<Contact>()
    }

}
