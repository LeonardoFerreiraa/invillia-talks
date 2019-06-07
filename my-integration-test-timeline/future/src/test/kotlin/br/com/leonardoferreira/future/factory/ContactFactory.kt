package br.com.leonardoferreira.future.factory

import br.com.leonardoferreira.future.domain.Contact
import br.com.leonardoferreira.future.repository.ContactRepository
import com.github.javafaker.Faker
import org.springframework.stereotype.Component

@Component
class ContactFactory(
    private val faker: Faker,
    contactRepository: ContactRepository
) : KBacon<Contact>(contactRepository) {

    override fun builder(): Contact {
        return Contact(
            name = faker.name().fullName(),
            email = faker.internet().emailAddress()
        )
    }

}
