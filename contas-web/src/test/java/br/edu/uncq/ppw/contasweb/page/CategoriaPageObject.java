package br.edu.uncq.ppw.contasweb.page;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CategoriaPageObject {

	private static PageObject pageObject;

	@BeforeClass
	public static void setUpClass() {
		pageObject = new PageObject();
	}

	@AfterClass
	public static void tearDownClass() {
		pageObject.fecharConexao();
	}

	@Test
	public void deveCadastrarCategoria() {
		pageObject.naveguePara("http://localhost:9090/contas-web/categoria/cadastrar.xhtml");
		WebElement campoNome = pageObject.aguarde(1).getDriver().findElement(By.id("categoria-nome"));
		pageObject.preenchaOCampoComValor(campoNome, "Viagem");
		WebElement botaoSalvar = pageObject.getDriver().findElement(By.id("btn-salvar-editar"));
		pageObject.cliqueEm(botaoSalvar);
		Assert.assertTrue(pageObject.aguarde(1).verifiqueSeTemOTexto("Categoria cadastrada com sucesso"));
	}

	@Test
	public void deveAtualizarCategoria() {
		pageObject.naveguePara("http://localhost:9090/contas-web/categoria/listar.xhtml");
		WebElement botaoEditar = pageObject.getDriver().findElement(By.id("categorias:0:btn-editar"));
		pageObject.cliqueEm(botaoEditar).aguarde(1);
		WebElement campoNome = pageObject.getDriver().findElement(By.id("categoria-nome"));
		pageObject.limpeOCampo(campoNome).preenchaOCampoComValor(campoNome, "Viagem internacional");
		WebElement botaoSalvar = pageObject.getDriver().findElement(By.id("btn-salvar-editar"));
		pageObject.cliqueEm(botaoSalvar);
		Assert.assertTrue(pageObject.aguarde(1).verifiqueSeTemOTexto("Categoria atualizada com sucesso"));
	}

	@Test
	public void deveNavegarParaPaginaCadastrarCategoria() {
		pageObject.naveguePara("http://localhost:9090/contas-web/categoria/cadastrar.xhtml");
	}

	@Test
	public void deveNavegarParaPaginaListarCategorias() {
		pageObject.naveguePara("http://localhost:9090/contas-web/categoria/listar.xhtml");
	}

}
