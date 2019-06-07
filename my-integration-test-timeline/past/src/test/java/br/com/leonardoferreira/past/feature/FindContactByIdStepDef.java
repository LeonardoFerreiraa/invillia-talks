package br.com.leonardoferreira.past.feature;

import br.com.leonardoferreira.past.domain.response.ContactResponse;
import br.com.leonardoferreira.past.repository.ContactRepository;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FindContactByIdStepDef {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ContactRepository contactRepository;

    private ResponseEntity<ContactResponse> response;

    @Dado("contato salvo no banco de dados")
    public void contatoSalvoNoBancoDeDados() {
        Assertions.assertThat(contactRepository.count())
                .isEqualTo(1L);
    }

    @Quando("^o usuário realiza a requisição para a busca de contato com o id \"([^\"]*)\"$")
    public void oUsuarioRealizaARequisicaoParaABuscaDeContato(Long id) {
        response = restTemplate.getForEntity("/contacts/" + id, ContactResponse.class);
    }

    @Então("o sistema deve retorna o usuário cadastrado no banco de dados")
    public void oSistemaDeveRetornaOUsuarioCadastradoNoBancoDeDados() {
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getName()).isEqualTo("teste");
        Assertions.assertThat(response.getBody().getEmail()).isEqualTo("teste@etset.com");
        Assertions.assertThat(response.getBody().getId()).isEqualTo(1);
    }

    @Então("o sistema deve retornar mensagem de erro dizendo que o contato não existe")
    public void oSistemaDeveRetornarMensagemDeErroDizendoQueOContatoNaoExiste() {
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
