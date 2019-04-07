package br.edu.uncq.ppw.contasweb.infra.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
