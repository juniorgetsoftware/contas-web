package br.edu.uncq.ppw.contasweb.page;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.edu.uncq.ppw.contasweb.driver.Chrome;

public final class PageObject {

	private WebDriver webDriver;

	public PageObject() {
		this.webDriver = new Chrome().getDriver();
	}

	public PageObject naveguePara(String url) {
		webDriver.get(url);
		return this;
	}

	public PageObject navegueParaPaginaExterna(String url) {
		webDriver.get(url);
		return this;
	}

	public boolean verifiqueSeTemOTexto(String texto) {
		return webDriver.getPageSource().contains(texto);
	}

	public boolean temTextoVisivel(WebElement webElement, String texto, int segundos) {
		WebDriverWait wait = new WebDriverWait(webDriver, segundos);
		return wait.until(ExpectedConditions.textToBePresentInElement(webElement, texto));
	}

	public boolean verifiqueSeTemOTitulo(String titulo) {
		return webDriver.getTitle().equals(titulo);
	}

	public PageObject limpeOsCampos(WebElement... webElements) {
		Stream.of(webElements).forEach(WebElement::clear);
		return this;
	}

	public PageObject limpeOCampo(WebElement webElement) {
		webElement.clear();
		return this;
	}

	public PageObject preenchaOCampoComValor(WebElement webElement, String valor) {
		webElement.sendKeys(valor);
		return this;
	}

	public String pegueOTextoDe(WebElement webElement) {
		return webElement.getText();
	}

	public PageObject cliqueEm(WebElement webElement) {
		webElement.click();
		return this;
	}

	public PageObject selecioneOComponente(WebElement webElement) {
		Actions acao = new Actions(webDriver);
		acao.moveToElement(webElement).perform();
		return this;
	}

	public PageObject submeta(WebElement webElement) {
		webElement.submit();
		return this;
	}

	/**
	 * Faz a execução aguardar por um tempo determinado
	 * 
	 * @param segundos Quantidade de segundos que a execução deverá aguardar
	 * @return
	 */
	public PageObject aguardeEquantoRenderizaOsComponentes(int segundos) {
		webDriver.manage().timeouts().implicitlyWait(segundos, SECONDS);
		return this;
	}

	public PageObject aguarde(long segundos) {
		try {
			Thread.sleep(segundos * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}

	public PageObject selecioneCheckboxPorNome(WebElement webElement) {
		webElement.click();
		return this;
	}

	public PageObject selecioneOItemComNome(WebElement webElement, String nome) {
		Select select = new Select(webElement);
		select.selectByVisibleText(nome);
		return this;
	}

	public PageObject selecioneOItemDoIndice(WebElement webElement, int indice) {
		Select select = new Select(webElement);
		select.selectByIndex(indice);
		return this;
	}

	public PageObject selecioneOPrimeiro(WebElement webElement) {
		selecioneOItemDoIndice(webElement, 0);
		return this;
	}

	public PageObject selecione(List<WebElement> findElements, int... itens) {
		for (int item : itens) {
			findElements.get(item).click();
		}
		return this;
	}

	public boolean verifiqueSeTemMensagem(WebElement webElement, String mensagem) {
		return temTextoVisivel(webElement, mensagem, 5);
	}

	public PageObject simuleTeclaEnter() {
		Actions acao = new Actions(webDriver);
		acao.sendKeys(Keys.ENTER);
		return this;
	}

	public boolean isElementoVisivel(WebElement webElement) {
		return webElement.isDisplayed();
	}

	public PageObject roleParaOElemento(WebElement webElement) {
		Actions actions = new Actions(webDriver);
		actions.moveToElement(webElement);
		actions.perform();
		return this;
	}

	public void fecharConexao() {
		webDriver.close();
	}

	public String getTitulo() {
		return webDriver.getTitle();
	}

	public WebDriver getDriver() {
		return webDriver;
	}
}