package br.edu.uncq.ppw.contasweb.repository;

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
	
	/*
	 select
		tipo_conta as 'tipo',
		sum(case when month(data_pagamento) = 1 then valor else 0 end) as 'JAN',
		sum(case when month(data_pagamento) = 2 then valor else 0 end) as 'FEV',
		sum(case when month(data_pagamento) = 3 then valor else 0 end) as 'MAR',
		sum(case when month(data_pagamento) = 4 then valor else 0 end) as 'ABR',
		sum(case when month(data_pagamento) = 5 then valor else 0 end) as 'MAI',
		sum(case when month(data_pagamento) = 6 then valor else 0 end) as 'JUN',
		sum(case when month(data_pagamento) = 7 then valor else 0 end) as 'JUL',
		sum(case when month(data_pagamento) = 8 then valor else 0 end) as 'AGO',
		sum(case when month(data_pagamento) = 9 then valor else 0 end) as 'SET',
		sum(case when month(data_pagamento) = 10 then valor else 0 end) as 'OUT',
		sum(case when month(data_pagamento) = 11 then valor else 0 end) as 'NOV',
		sum(case when month(data_pagamento) = 12 then valor else 0 end) as 'DEZ',
		year(data_pagamento) as 'ano'
	from
		conta
	where
		data_pagamento is not null
	group by
		ano,
		tipo_conta;
	 */
	public List<SomatorioContasAnual> contasAnual(Integer ano) {
		return getEntityManager().createQuery(
				"SELECT new br.edu.uncq.ppw.contasweb.model.dto.SomatorioContasAnual("
				+ "tipoConta as 'tipo', "
				+ "sum(case when month(data_pagamento) = 1 then valor else 0 end) as 'jan',"
				+ "sum(case when month(data_pagamento) = 2 then valor else 0 end) as 'fev',"
				+ "sum(case when month(data_pagamento) = 3 then valor else 0 end) as 'mar',"
				+ "sum(case when month(data_pagamento) = 4 then valor else 0 end) as 'abr',"
				+ "sum(case when month(data_pagamento) = 5 then valor else 0 end) as 'mai',"
				+ "sum(case when month(data_pagamento) = 6 then valor else 0 end) as 'jun',"
				+ "sum(case when month(data_pagamento) = 7 then valor else 0 end) as 'jul',"
				+ "sum(case when month(data_pagamento) = 8 then valor else 0 end) as 'ago',"
				+ "sum(case when month(data_pagamento) = 9 then valor else 0 end) as 'set',"
				+ "sum(case when month(data_pagamento) = 10 then valor else 0 end) as 'out',"
				+ "sum(case when month(data_pagamento) = 11 then valor else 0 end) as 'nov',"
				+ "sum(case when month(data_pagamento) = 11 then valor else 0 end) as 'dez',"
				+ "year(data_pagamento) as 'ano' "
				+ ") from conta "
				+ "where dataPagamento in not null "
				+ "and year(dataPagamento) = :ANO"
				+ "group by ano, tipoConta"
				, SomatorioContasAnual.class)
				.setParameter("ANO", ano)
				.getResultList();
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
