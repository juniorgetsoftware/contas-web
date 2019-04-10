package br.edu.uncq.ppw.contasweb.repository;

import static java.util.Optional.ofNullable;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.edu.uncq.ppw.contasweb.infra.jpa.JpaUtil;
import br.edu.uncq.ppw.contasweb.model.Mes;
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

	public BigDecimal totalReceitaPorMes(Mes mes, Integer ano) {
		return totalPorTipoEMes(TipoConta.RECEITA, mes, ano);
	}

	public BigDecimal totalDespesaPorMes(Mes mes, Integer ano) {
		return totalPorTipoEMes(TipoConta.DESPESA, mes, ano);
	}

	private BigDecimal totalPorTipoEMes(TipoConta tipo, Mes mes, Integer ano) {
		return ofNullable(getEntityManager().createQuery(
				"SELECT sum(c.valor) FROM conta c WHERE c.tipoConta = :TIPO AND MONTH(c.dataPagamento) = :MES AND YEAR(c.dataPagamento) = :ANO",
				BigDecimal.class).setParameter("TIPO", tipo).setParameter("ANO", ano)
				.setParameter("MES", mes.getNumero()).getSingleResult()).orElse(BigDecimal.ZERO);
	}

	public List<SomatorioValorContaPorCategoria> totalDespesaPorCategoriaEMes(Mes mes, Integer ano) {
		return totalPorCategoriaEMes(TipoConta.DESPESA, mes, ano);
	}

	public List<SomatorioValorContaPorCategoria> totalReceitaPorCategoriaEMes(Mes mes, Integer ano) {
		return totalPorCategoriaEMes(TipoConta.RECEITA, mes, ano);
	}

	private List<SomatorioValorContaPorCategoria> totalPorCategoriaEMes(TipoConta tipo, Mes mes, Integer ano) {
		return getEntityManager().createQuery(
				"SELECT new br.edu.uncq.ppw.contasweb.model.dto.SomatorioValorContaPorCategoria(ca.nome, sum(co.valor)) from conta co join co.categoria ca WHERE co.tipoConta = :TIPO AND MONTH(co.dataPagamento) = :MES AND YEAR(co.dataPagamento) = :ANO group by ca.nome",
				SomatorioValorContaPorCategoria.class).setParameter("TIPO", tipo).setParameter("ANO", ano)
				.setParameter("MES", mes.getNumero()).getResultList();
	}

}
