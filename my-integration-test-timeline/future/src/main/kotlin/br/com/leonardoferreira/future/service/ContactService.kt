package br.com.leonardoferreira.future.service

import br.com.leonardoferreira.future.domain.Contact
import br.com.leonardoferreira.future.domain.request.ContactRequest
import br.com.leonardoferreira.future.domain.response.ContactResponse
import br.com.leonardoferreira.future.exception.ResourceNotFoundException
import br.com.leonardoferreira.future.repository.ContactRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.format.DateTimeFormatter

@Service
class ContactService(
    private val contactRepository: ContactRepository
) {

    @Transactional(readOnly = true)
    fun findById(id: Long): ContactResponse {
        val contact = (contactRepository.findByIdOrNull(id)
            ?: throw ResourceNotFoundException())

        val pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        return ContactResponse(
            id = contact.id,
            name = contact.name,
            email = contact.email,
            createdAt = contact.createdAt.format(pattern),
            updatedAt = contact.updatedAt.format(pattern)
        )
    }

    @Transactional
    fun create(contactRequest: ContactRequest): Long {
        val contact = Contact(
            name = contactRequest.name,
            email = contactRequest.email
        )

        contactRepository.save(contact)

        return contact.id
    }

}
