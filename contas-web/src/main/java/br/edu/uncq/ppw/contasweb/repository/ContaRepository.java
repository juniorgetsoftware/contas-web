package br.edu.uncq.ppw.contasweb.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.edu.uncq.ppw.contasweb.infra.jpa.JpaUtil;
import br.edu.uncq.ppw.contasweb.model.Conta;

public class ContaRepository {

	private EntityManager entityManager;

	public ContaRepository() {
		this.entityManager = new JpaUtil().getEntityManager();
	}

	public ContaRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Conta salvarOuAtualizar(Conta conta) {
		entityManager.getTransaction().begin();
		Conta contaMerge = entityManager.merge(conta);
		entityManager.getTransaction().commit();
		entityManager.close();
		return contaMerge;
	}

	public Conta contaPorId(Long id) {
		entityManager.getTransaction().begin();
		Conta contaDeletada = entityManager.find(Conta.class, id);
		entityManager.getTransaction().commit();
		entityManager.close();
		return contaDeletada;
	}

	public List<Conta> contas() {
		List<Conta> contas = entityManager.createQuery("from conta", Conta.class).getResultList();
		entityManager.close();
		return contas;
	}

	public void deletar(Conta conta) {
		conta = contaPorId(conta.getId());
		entityManager.remove(conta);
		entityManager.close();
	}
}
