package br.edu.uncq.ppw.contasweb.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.edu.uncq.ppw.contasweb.infra.jpa.JpaUtil;
import br.edu.uncq.ppw.contasweb.model.Categoria;

public class CategoriaRepository {

	private EntityManager entityManager;

	public CategoriaRepository() {
		this.entityManager = new JpaUtil().getEntityManager();
	}

	public CategoriaRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Categoria salvarOuAtualizar(Categoria categoria) {
		entityManager.getTransaction().begin();
		Categoria categoriaMerge = entityManager.merge(categoria);
		entityManager.getTransaction().commit();
		entityManager.close();
		return categoriaMerge;
	}

	public Categoria categoriaPorId(Long id) {
		entityManager.getTransaction().begin();
		Categoria categoriaDeletada = entityManager.find(Categoria.class, id);
		entityManager.getTransaction().commit();
		entityManager.close();
		return categoriaDeletada;
	}

	public List<Categoria> categorias() {
		List<Categoria> categorias = entityManager.createQuery("from categoria", Categoria.class).getResultList();
		entityManager.close();
		return categorias;
	}

	public void deletar(Categoria categoria) {
		categoria = categoriaPorId(categoria.getId());
		entityManager.remove(categoria);
		entityManager.close();
	}
}
