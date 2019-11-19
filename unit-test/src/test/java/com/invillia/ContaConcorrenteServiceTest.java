package com.invillia;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

class ContaConcorrenteServiceTest {

    private ContaCorrenteRepository contaCorrenteRepository;
    private ContaConcorrente contaConcorrente;
    private ContaCorrenteService contaCorrenteService;

    @BeforeEach
    void setUp() {
        contaCorrenteRepository = Mockito.mock(ContaCorrenteRepository.class);
        contaConcorrente = new ContaConcorrente(1000.00);
        contaCorrenteService = new ContaCorrenteService(contaCorrenteRepository);
    }

    @Test
    void sacarComSucessoTest() {
        contaCorrenteService.sacar(contaConcorrente, 100.00);

        Assertions.assertEquals(900.00, contaConcorrente.getSaldo());

        final ArgumentCaptor<ContaConcorrente> captor = ArgumentCaptor.forClass(ContaConcorrente.class);
        Mockito.verify(contaCorrenteRepository, Mockito.times(1))
                .save(captor.capture());
        final ContaConcorrente contaPassada = captor.getValue();
        Assertions.assertEquals(contaConcorrente.getSaldo(), contaPassada.getSaldo());
    }

    @Test
    void sacarSemLimiteTest() {
        contaCorrenteService.sacar(contaConcorrente, 1100.00);

        Assertions.assertEquals(1000.00, contaConcorrente.getSaldo());
    }

}
