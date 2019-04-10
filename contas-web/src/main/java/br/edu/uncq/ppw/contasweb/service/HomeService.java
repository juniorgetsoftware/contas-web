package br.edu.uncq.ppw.contasweb.service;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

import br.edu.uncq.ppw.contasweb.model.dto.SomatorioValorContaPorCategoria;
import br.edu.uncq.ppw.contasweb.repository.HomeRepository;

public class HomeService {

	public HomeService() {
		this.homeRepository = new HomeRepository();
	}

	private HomeRepository homeRepository;

	public BigDecimal totalReceitaPorMes(Month mes) {
		return this.homeRepository.totalReceitaPorMes(mes);
	}

	public BigDecimal totalDespesaPorMes(Month mes) {
		return this.homeRepository.totalDespesaPorMes(mes);
	}

	public List<SomatorioValorContaPorCategoria> totalDespesaPorCategoriaEMes(Month mes) {
		return this.homeRepository.totalDespesaPorCategoriaEMes(mes);
	}

	public List<SomatorioValorContaPorCategoria> totalReceitaPorCategoriaEMes(Month mes) {
		return this.homeRepository.totalReceitaPorCategoriaEMes(mes);
	}

}
