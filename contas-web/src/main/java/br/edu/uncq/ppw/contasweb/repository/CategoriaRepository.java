package br.edu.uncq.ppw.contasweb.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.edu.uncq.ppw.contasweb.infra.jpa.JpaUtil;
import br.edu.uncq.ppw.contasweb.model.Categoria;

public class CategoriaRepository {

	private EntityManager entityManager;

	public CategoriaRepository() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	public CategoriaRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Categoria salvarOuAtualizar(Categoria categoria) {
		Categoria categoriaMerge = null;
		try {
			getEntityManager().getTransaction().begin();
			categoriaMerge = getEntityManager().merge(categoria);
			getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			getEntityManager().getTransaction().rollback();
			e.printStackTrace();
		} finally {
			getEntityManager().close();
		}
		return categoriaMerge;
	}

	public Categoria categoriaPorId(Long id) {
		Categoria categoriaDeletada = null;
		try {
			categoriaDeletada = getEntityManager().find(Categoria.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getEntityManager().close();
		}
		return categoriaDeletada;
	}

	public List<Categoria> categorias() {
		List<Categoria> categorias = getEntityManager().createQuery("from categoria", Categoria.class).getResultList();
		getEntityManager().close();
		return categorias;
	}

	public void deletar(Categoria categoria) {
		try {
			categoria = getEntityManager().find(Categoria.class, categoria.getId());
			getEntityManager().getTransaction().begin();
			getEntityManager().remove(categoria);
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
