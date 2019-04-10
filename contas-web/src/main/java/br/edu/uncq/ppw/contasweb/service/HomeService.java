package br.edu.uncq.ppw.contasweb.service;

import java.math.BigDecimal;
import java.util.List;

import br.edu.uncq.ppw.contasweb.model.Mes;
import br.edu.uncq.ppw.contasweb.model.dto.SomatorioValorContaPorCategoria;
import br.edu.uncq.ppw.contasweb.repository.HomeRepository;

public class HomeService {

	public HomeService() {
		this.homeRepository = new HomeRepository();
	}

	private HomeRepository homeRepository;

	public BigDecimal totalReceitaPorMes(Mes mes, Integer ano) {
		return this.homeRepository.totalReceitaPorMes(mes, ano);
	}

	public BigDecimal totalDespesaPorMes(Mes mes, Integer ano) {
		return this.homeRepository.totalDespesaPorMes(mes, ano);
	}

	public List<SomatorioValorContaPorCategoria> totalDespesaPorCategoriaEMes(Mes mes, Integer ano) {
		return this.homeRepository.totalDespesaPorCategoriaEMes(mes, ano);
	}

	public List<SomatorioValorContaPorCategoria> totalReceitaPorCategoriaEMes(Mes mes, Integer ano) {
		return this.homeRepository.totalReceitaPorCategoriaEMes(mes, ano);
	}

}
