package br.com.leonardoferreira.past.feature;

import br.com.leonardoferreira.past.domain.Contact;
import br.com.leonardoferreira.past.domain.request.ContactRequest;
import br.com.leonardoferreira.past.repository.ContactRepository;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateContactStepDef {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ContactRepository contactRepository;

    private ContactRequest contact;

    private ResponseEntity<Map> response;

    @Dado("dados validos para cadastro de contato")
    public void dadosValidosParaCadastroDeContato() {
        contact = new ContactRequest();
        contact.setName("qwiqwj");
        contact.setEmail("qweqwe@qpiwejqiwje.com");
    }

    @Quando("o usuário faz uma requisição para cadastro de contato")
    public void oUsuarioFazUmaRequisicaoParaCadastroDeContato() {
        response = restTemplate.postForEntity("/contacts", contact, Map.class);
    }

    @Então("o contato é salvo no banco de dados")
    public void oContatoESalvoNoBancoDeDados() {
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(contactRepository.count()).isEqualTo(2L);
        final Contact dbContact = contactRepository.findById(2L).orElse(null);
        Assertions.assertThat(dbContact).isNotNull();
        Assertions.assertThat(dbContact.getName()).isEqualTo(contact.getName());
        Assertions.assertThat(dbContact.getEmail()).isEqualTo(contact.getEmail());
        Assertions.assertThat(dbContact.getId()).isNotNull();
        Assertions.assertThat(dbContact.getCreatedAt()).isNotNull();
        Assertions.assertThat(dbContact.getUpdatedAt()).isNotNull();
    }

    @Dado("dados invalidos para cadastro de contato")
    public void dadosInvalidosParaCadastroDeContato() {
        contact = new ContactRequest();
        contact.setEmail("not valid");
    }

    @SuppressWarnings("unchecked")
    @Então("o sistema deve retornar erros de validacao para o cadastro de contato")
    public void oSistemaDeveRetornarErrosDeValidacaoParaOCadastroDeContato() {
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        List<Map> errors = (List<Map>) response.getBody().get("errors");
        Assertions.assertThat(errors).isNotEmpty();
        Assertions.assertThat(errors.size()).isEqualTo(2);

        errors.forEach(error -> {
            if ("name".equals(error.get("field"))) {
                Assertions.assertThat(error.get("defaultMessage"))
                        .isEqualTo("must not be blank");
            } else if ("email".equals(error.get("field"))) {
                Assertions.assertThat(error.get("defaultMessage"))
                        .isEqualTo("must be a well-formed email address");
            } else {
                Assertions.fail("Field não esperado.");
            }
        });
    }
}
