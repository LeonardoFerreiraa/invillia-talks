package com.invillia;

public class ContaCorrenteService {

    private final ContaCorrenteRepository contaCorrenteRepository;

    public ContaCorrenteService(final ContaCorrenteRepository contaCorrenteRepository) {
        this.contaCorrenteRepository = contaCorrenteRepository;
    }

    public void sacar(final ContaConcorrente contaConcorrente, final Double valor) {
        if (valor <= contaConcorrente.getSaldo()) {
            contaConcorrente.setSaldo(contaConcorrente.getSaldo() - valor);
        }

        contaCorrenteRepository.save(contaConcorrente);
    }

}
