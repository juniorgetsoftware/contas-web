package br.edu.uncq.ppw.contasweb.repository;

import static br.edu.uncq.ppw.contasweb.model.TipoConta.DESPESA;
import static br.edu.uncq.ppw.contasweb.model.TipoConta.RECEITA;
import static java.math.BigDecimal.ZERO;
import static java.util.Optional.ofNullable;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.edu.uncq.ppw.contasweb.infra.jpa.JpaUtil;
import br.edu.uncq.ppw.contasweb.model.Mes;
import br.edu.uncq.ppw.contasweb.model.TipoConta;
import br.edu.uncq.ppw.contasweb.model.dto.SomatorioContasAnual;
import br.edu.uncq.ppw.contasweb.model.dto.SomatorioValorContaPorCategoria;

public class HomeRepository {

	private static final boolean MAX = true;
	private static final boolean MIN = false;

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

	public List<SomatorioContasAnual> contasAnual(Integer ano) {
		return getEntityManager()
				.createQuery(
						"SELECT new br.edu.uncq.ppw.contasweb.model.dto.SomatorioContasAnual(" + "tipoConta, "
								+ "sum(case when month(dataPagamento) = 1 then valor else 0 end), "
								+ "sum(case when month(dataPagamento) = 2 then valor else 0 end), "
								+ "sum(case when month(dataPagamento) = 3 then valor else 0 end), "
								+ "sum(case when month(dataPagamento) = 4 then valor else 0 end), "
								+ "sum(case when month(dataPagamento) = 5 then valor else 0 end), "
								+ "sum(case when month(dataPagamento) = 6 then valor else 0 end), "
								+ "sum(case when month(dataPagamento) = 7 then valor else 0 end), "
								+ "sum(case when month(dataPagamento) = 8 then valor else 0 end), "
								+ "sum(case when month(dataPagamento) = 9 then valor else 0 end), "
								+ "sum(case when month(dataPagamento) = 10 then valor else 0 end), "
								+ "sum(case when month(dataPagamento) = 11 then valor else 0 end), "
								+ "sum(case when month(dataPagamento) = 12 then valor else 0 end), "
								+ "year(dataPagamento)) " + "from conta where dataPagamento is not null "
								+ "and year(dataPagamento) = :ANO " + "group by year(dataPagamento), tipoConta ",
						SomatorioContasAnual.class)
				.setParameter("ANO", ano).getResultList();
	}

	public BigDecimal totalReceitaPorMes(Mes mes, Integer ano) {
		return totalPorTipoEMes(RECEITA, mes, ano);
	}

	public BigDecimal totalDespesaPorMes(Mes mes, Integer ano) {
		return totalPorTipoEMes(DESPESA, mes, ano);
	}

	private BigDecimal totalPorTipoEMes(TipoConta tipo, Mes mes, Integer ano) {
		return ofNullable(getEntityManager().createQuery(
				"SELECT sum(c.valor) FROM conta c WHERE c.tipoConta = :TIPO AND MONTH(c.dataPagamento) = :MES AND YEAR(c.dataPagamento) = :ANO",
				BigDecimal.class).setParameter("TIPO", tipo).setParameter("ANO", ano)
				.setParameter("MES", mes.getNumero()).getSingleResult()).orElse(ZERO);
	}

	public List<SomatorioValorContaPorCategoria> totalDespesaPorCategoriaEMes(Mes mes, Integer ano) {
		return totalPorCategoriaEMes(DESPESA, mes, ano);
	}

	public List<SomatorioValorContaPorCategoria> totalReceitaPorCategoriaEMes(Mes mes, Integer ano) {
		return totalPorCategoriaEMes(RECEITA, mes, ano);
	}

	private List<SomatorioValorContaPorCategoria> totalPorCategoriaEMes(TipoConta tipo, Mes mes, Integer ano) {
		return getEntityManager().createQuery(
				"SELECT new br.edu.uncq.ppw.contasweb.model.dto.SomatorioValorContaPorCategoria(ca.nome, sum(co.valor)) from conta co join co.categoria ca WHERE co.tipoConta = :TIPO AND MONTH(co.dataPagamento) = :MES AND YEAR(co.dataPagamento) = :ANO group by ca.nome",
				SomatorioValorContaPorCategoria.class).setParameter("TIPO", tipo).setParameter("ANO", ano)
				.setParameter("MES", mes.getNumero()).getResultList();
	}

	public BigDecimal maiorReceitaDoMesNoAno(Mes mes, Integer ano) {
		return contaDoMesNoAnoBase(MAX, mes, ano, RECEITA);
	}

	public BigDecimal menorReceitaDoMesNoAno(Mes mes, Integer ano) {
		return contaDoMesNoAnoBase(MIN, mes, ano, RECEITA);
	}

	public BigDecimal maiorDespesaDoMesNoAno(Mes mes, Integer ano) {
		return contaDoMesNoAnoBase(MAX, mes, ano, DESPESA);
	}

	public BigDecimal menorDespesaDoMesNoAno(Mes mes, Integer ano) {
		return contaDoMesNoAnoBase(MIN, mes, ano, DESPESA);
	}

	public BigDecimal contaDoMesNoAnoBase(boolean isMax, Mes mes, Integer ano, TipoConta tipo) {
		return getEntityManager().createQuery("select " + (isMax ? "max" : "min")
				+ "(valor) from conta where month(dataPagamento) = :MES and year(dataPagamento) = :ANO and tipoConta = :TIPO",
				BigDecimal.class).setParameter("MES", mes.getNumero()).setParameter("ANO", ano)
				.setParameter("TIPO", tipo).getSingleResult();
	}

}
