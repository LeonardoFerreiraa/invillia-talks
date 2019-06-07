package br.com.leonardoferreira.future.factory

import br.com.leonardoferreira.future.domain.request.ContactRequest
import com.github.javafaker.Faker
import org.springframework.stereotype.Component

@Component
class ContactRequestFactory(
    private val faker: Faker
) : KBacon<ContactRequest>() {

    override fun builder(): ContactRequest {
        return ContactRequest(
            name = faker.name().fullName(),
            email = faker.internet().emailAddress()
        )
    }

    fun invalid(): ContactRequest {
        return ContactRequest(
            name = "",
            email = ""
        )
    }

}
