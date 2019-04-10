package br.edu.uncq.ppw.contasweb.builder;

import static br.edu.uncq.ppw.contasweb.model.TipoConta.DESPESA;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.uncq.ppw.contasweb.model.Categoria;
import br.edu.uncq.ppw.contasweb.model.Conta;
import br.edu.uncq.ppw.contasweb.model.TipoConta;

public class ContaBuilder {

	private ContaBuilder() {
		conta = new Conta();
	}

	private Conta conta;

	public static ContaBuilder novaDespesaPaga__125_30() {
		ContaBuilder c = new ContaBuilder();
		c.conta.setTitulo("Nova conta");
		c.conta.setCategoria(new Categoria(1L));
		c.conta.setDataPagamento(new Date());
		c.conta.setDataVencimento(new Date());
		c.conta.setQuitada(true);
		c.conta.setTipoConta(DESPESA);
		c.conta.setValor(new BigDecimal("125.30"));
		return c;
	}

	public ContaBuilder comTitulo(String titulo) {
		conta.setTitulo(titulo);
		return this;
	}

	public ContaBuilder comCategoria(Categoria categoria) {
		conta.setCategoria(categoria);
		return this;
	}

	public ContaBuilder comDataPagamento(String data) {
		conta.setDataPagamento(stringParaData(data));
		return this;
	}

	public Date stringParaData(String data) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ContaBuilder comDataVencimento(String data) {
		conta.setDataVencimento(stringParaData(data));
		return this;
	}

	public ContaBuilder comId(Long id) {
		conta.setId(id);
		return this;
	}

	public ContaBuilder quitada() {
		conta.setQuitada(true);
		return this;
	}

	public ContaBuilder naoQuitada() {
		conta.setQuitada(false);
		return this;
	}

	public ContaBuilder comTipoReceita() {
		conta.setTipoConta(TipoConta.RECEITA);
		return this;
	}

	public ContaBuilder comTipoDespesa() {
		conta.setTipoConta(TipoConta.DESPESA);
		return this;
	}

	public ContaBuilder com(String valor) {
		conta.setValor(new BigDecimal(valor));
		return this;
	}

	public Conta build() {
		return conta;
	}

	public static ContaBuilder novoConta() {
		return new ContaBuilder();
	}

}
