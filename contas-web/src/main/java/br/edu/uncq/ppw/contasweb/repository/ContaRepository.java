package br.edu.uncq.ppw.contasweb.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.edu.uncq.ppw.contasweb.infra.jpa.JpaUtil;
import br.edu.uncq.ppw.contasweb.model.Conta;

public class ContaRepository {

	private EntityManager entityManager;

	public ContaRepository() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	public ContaRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Conta salvarOuAtualizar(Conta conta) {
		Conta contaMerge = null;
		try {
			getEntityManager().getTransaction().begin();
			contaMerge = getEntityManager().merge(conta);
			getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			getEntityManager().getTransaction().rollback();
			e.printStackTrace();
		} finally {
			getEntityManager().close();
		}
		return contaMerge;
	}

	public Conta contaPorId(Long id) {
		Conta contaDeletada = null;
		try {
			contaDeletada = getEntityManager().find(Conta.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getEntityManager().close();
		}
		return contaDeletada;
	}

	public List<Conta> contas() {
		List<Conta> contas = getEntityManager().createQuery("from conta", Conta.class).getResultList();
		getEntityManager().close();
		return contas;
	}

	public void deletar(Conta conta) {
		try {
			conta = contaPorId(conta.getId());
			getEntityManager().getTransaction().begin();
			getEntityManager().remove(conta);
			getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			getEntityManager().getTransaction().rollback();
		} finally {
			getEntityManager().close();
		}

	}

	public EntityManager getEntityManager() {
		if (entityManager == null || !entityManager.isOpen()) {
			entityManager = JpaUtil.getEntityManager();
		}
		return entityManager;
	}
}
