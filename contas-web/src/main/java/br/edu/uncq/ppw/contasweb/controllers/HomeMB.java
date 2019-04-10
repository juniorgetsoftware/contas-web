package br.edu.uncq.ppw.contasweb.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.uncq.ppw.contasweb.model.Mes;
import br.edu.uncq.ppw.contasweb.model.dto.SomatorioValorContaPorCategoria;
import br.edu.uncq.ppw.contasweb.service.HomeService;

@ManagedBean
@ViewScoped
public class HomeMB {

	private HomeService homeService = new HomeService();
	private Mes mes = Mes.mesPorNumero(LocalDate.now().getMonthValue());;
	private Integer ano = LocalDate.now().getYear();
	private List<SomatorioValorContaPorCategoria> totalReceitaPorCategoriaEMes;
	private Iterable<SomatorioValorContaPorCategoria> totalDespesaPorCategoriaEMes;
	private BigDecimal totalReceitasPorMes;
	private BigDecimal totalDespesasPorMes;

	@PostConstruct
	public void init() {
		totalReceitaPorCategoriaEMes = homeService.totalReceitaPorCategoriaEMes(mes, ano);
		totalDespesaPorCategoriaEMes = homeService.totalDespesaPorCategoriaEMes(mes, ano);
		totalReceitasPorMes = homeService.totalReceitaPorMes(mes, ano);
		totalDespesasPorMes = homeService.totalDespesaPorMes(mes, ano);
	}

	public Mes getMes() {
		return mes;
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

	public Mes[] mese() {
		return Mes.values();
	}

	public Integer[] anos() {
		Integer anoAterior = ano - 1;
		Integer anoAtual = ano;
		Integer proximoAno = ano + 1;
		return new Integer[] { anoAterior, anoAtual, proximoAno };
	}

	public List<SomatorioValorContaPorCategoria> getTotalReceitaPorCategoriaEMes() {
		return totalReceitaPorCategoriaEMes;
	}

	public void setTotalReceitaPorCategoriaEMes(List<SomatorioValorContaPorCategoria> totalReceitaPorCategoriaEMes) {
		this.totalReceitaPorCategoriaEMes = totalReceitaPorCategoriaEMes;
	}

	public Iterable<SomatorioValorContaPorCategoria> getTotalDespesaPorCategoriaEMes() {
		return totalDespesaPorCategoriaEMes;
	}

	public void setTotalDespesaPorCategoriaEMes(
			Iterable<SomatorioValorContaPorCategoria> totalDespesaPorCategoriaEMes) {
		this.totalDespesaPorCategoriaEMes = totalDespesaPorCategoriaEMes;
	}

	public BigDecimal getTotalReceitasPorMes() {
		return totalReceitasPorMes;
	}

	public void setTotalReceitasPorMes(BigDecimal totalReceitasPorMes) {
		this.totalReceitasPorMes = totalReceitasPorMes;
	}

	public BigDecimal getTotalDespesasPorMes() {
		return totalDespesasPorMes;
	}

	public void setTotalDespesasPorMes(BigDecimal totalDespesasPorMes) {
		this.totalDespesasPorMes = totalDespesasPorMes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}
}
