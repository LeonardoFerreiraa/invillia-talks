package br.com.leonardoferreira.future.integration

import br.com.leonardoferreira.future.assertion.ContactAssert
import br.com.leonardoferreira.future.dsl.restAssured
import br.com.leonardoferreira.future.exception.ResourceNotFoundException
import br.com.leonardoferreira.future.factory.ContactRequestFactory
import br.com.leonardoferreira.future.repository.ContactRepository
import io.restassured.http.ContentType
import org.hamcrest.Matchers.containsInAnyOrder
import org.hamcrest.Matchers.endsWith
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST
import javax.servlet.http.HttpServletResponse.SC_CREATED

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreateContactIntegrationTest @Autowired constructor(
    private val contactRepository: ContactRepository,
    private val contactRequestFactory: ContactRequestFactory
) {

    @Test
    fun `create contact with success`() {
        val request = contactRequestFactory.create()

        restAssured {
            given {
                contentType(ContentType.JSON)
                body(request)
            }
            on {
                post("/contacts")
            }
            then {
                statusCode(SC_CREATED)
                header("location", endsWith("/contacts/1"))
            }
        }

        val contact = (contactRepository.findByIdOrNull(1L)
            ?: throw ResourceNotFoundException())

        ContactAssert(contact).assertThat {
            hasName(request.name)
            hasEmail(request.email)
        }
    }

    @Test
    fun `fail in validations`() {
        val request = contactRequestFactory.create(contactRequestFactory::invalid)
        restAssured {
            given {
                contentType(ContentType.JSON)
                body(request)
            }
            on {
                post("/contacts")
            }
            then {
                statusCode(SC_BAD_REQUEST)
                body("name", containsInAnyOrder("must not be blank"))
                body("email", containsInAnyOrder("must not be blank"))
            }
        }
    }

}
