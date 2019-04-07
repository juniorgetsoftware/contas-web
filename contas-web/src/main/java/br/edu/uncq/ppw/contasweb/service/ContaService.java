package br.edu.uncq.ppw.contasweb.service;

import java.util.List;

import br.edu.uncq.ppw.contasweb.model.Conta;
import br.edu.uncq.ppw.contasweb.repository.ContaRepository;

public class ContaService {

	private ContaRepository contaRepository;

	public ContaService() {
		this.contaRepository = new ContaRepository();
	}

	public Conta salvarOuAtualizar(Conta conta) {
		return this.contaRepository.salvarOuAtualizar(conta);
	}

	public Conta contaPorId(Long id) {
		return this.contaRepository.contaPorId(id);
	}

	public void deletar(Conta conta) {
		this.contaRepository.deletar(conta);
	}

	public List<Conta> contas() {
		return this.contaRepository.contas();
	}

}
