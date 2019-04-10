package br.edu.uncq.ppw.contasweb.repository;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.edu.uncq.ppw.contasweb.model.Categoria;
import br.edu.uncq.ppw.contasweb.model.Conta;
import br.edu.uncq.ppw.contasweb.model.TipoConta;

public class ContaRepositoryTest {

	private static ContaRepository repository;

	@BeforeClass
	public static void setUpClass() {
		repository = new ContaRepository();
	}

	@AfterClass
	public static void teardownClass() {
		repository = null;
	}

	@Test
	public void deveAdicionarUmaConta() {
		Conta conta = new Conta();
		conta.setCategoria(new Categoria(1L));
		conta.setDataPagamento(new Date());
		conta.setDataVencimento(new Date());
		conta.setQuitada(true);
		conta.setTipoConta(TipoConta.DESPESA);
		conta.setTitulo("Nova conta");
		conta.setValor(new BigDecimal("125.30"));
		Conta contaSalva = repository.salvarOuAtualizar(conta);

		Conta contaPorId = repository.contaPorId(contaSalva.getId());

		Assert.assertEquals(contaPorId, contaSalva);
	}
}
