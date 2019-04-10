package br.edu.uncq.ppw.contasweb.model.dto;

import java.math.BigDecimal;

public class SomatorioValorContaPorCategoria {

	private String categoria;
	private BigDecimal valor;

	public SomatorioValorContaPorCategoria(String categoria, BigDecimal valor) {
		super();
		this.categoria = categoria;
		this.valor = valor;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
