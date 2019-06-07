package br.com.leonardoferreira.future.assertion

import br.com.leonardoferreira.future.domain.Contact

class ContactAssert(actual: Contact) : BaseAssert<ContactAssert, Contact>(actual, ContactAssert::class) {

    fun hasName(name: String) = assertAll {
        objects.assertEqual(field("contact.name"), actual.name, name)
    }

    fun hasEmail(email: String) = assertAll {
        objects.assertEqual(field("contact.email"), actual.email, email)
    }

}
