package br.edu.uncq.ppw.contasweb.repository;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

import javax.persistence.EntityManager;

import br.edu.uncq.ppw.contasweb.infra.jpa.JpaUtil;
import br.edu.uncq.ppw.contasweb.model.TipoConta;
import br.edu.uncq.ppw.contasweb.model.dto.SomatorioValorContaPorCategoria;

public class HomeRepository {

	private EntityManager entityManager;

	public HomeRepository() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	public HomeRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		if (entityManager == null || !entityManager.isOpen()) {
			entityManager = JpaUtil.getEntityManager();
		}
		return entityManager;
	}


	public BigDecimal totalReceitaPorMes(Month mes) {
		return totalPorTipoEMes(TipoConta.RECEITA, mes);
	}

	public BigDecimal totalDespesaPorMes(Month mes) {
		return totalPorTipoEMes(TipoConta.DESPESA, mes);
	}

	private BigDecimal totalPorTipoEMes(TipoConta tipo, Month mes) {
		return getEntityManager()
				.createQuery(
						"SELECT sum(c.valor) FROM conta c WHERE c.tipoConta = :TIPO AND MONTH(c.dataPagamento) = :MES",
						BigDecimal.class)
				.setParameter("TIPO", tipo).setParameter("MES", mes.getValue()).getSingleResult();
	}

	public List<SomatorioValorContaPorCategoria> totalDespesaPorCategoriaEMes(Month mes) {
		return totalPorCategoriaEMes(TipoConta.DESPESA, mes);
	}

	public List<SomatorioValorContaPorCategoria> totalReceitaPorCategoriaEMes(Month mes) {
		return totalPorCategoriaEMes(TipoConta.RECEITA, mes);
	}

	private List<SomatorioValorContaPorCategoria> totalPorCategoriaEMes(TipoConta tipo, Month mes) {
		return getEntityManager().createQuery(
				"SELECT new br.edu.uncq.ppw.contasweb.model.dto.SomatorioValorContaPorCategoria(ca.nome, sum(co.valor)) from conta co join co.categoria ca WHERE co.tipoConta = :TIPO AND MONTH(co.dataPagamento) = :MES group by ca.nome",
				SomatorioValorContaPorCategoria.class).setParameter("TIPO", tipo).setParameter("MES", mes.getValue())
				.getResultList();
	}

}
