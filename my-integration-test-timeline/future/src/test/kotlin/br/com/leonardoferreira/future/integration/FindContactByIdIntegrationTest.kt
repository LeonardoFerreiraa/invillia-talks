package br.com.leonardoferreira.future.integration

import br.com.leonardoferreira.future.dsl.restAssured
import br.com.leonardoferreira.future.factory.ContactFactory
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.servlet.http.HttpServletResponse.SC_NOT_FOUND
import javax.servlet.http.HttpServletResponse.SC_OK

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FindContactByIdIntegrationTest @Autowired constructor(
    private val contactFactory: ContactFactory
) {

    @Test
    fun `test find contact by id`() {
        val contact = contactFactory.create()

        restAssured {
            given {

            }
            on {
                get("/contacts/1")
            }
            then {
                statusCode(SC_OK)
                body("name", equalTo(contact.name))
            }
        }
    }

    @Test
    fun `test find not found contact`() {
        restAssured {
            given {

            }
            on {
                get("contacts/1")
            }
            then {
                statusCode(SC_NOT_FOUND)
            }
        }
    }

}
