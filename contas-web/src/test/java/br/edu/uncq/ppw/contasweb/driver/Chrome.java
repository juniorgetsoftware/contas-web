package br.edu.uncq.ppw.contasweb.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Chrome {

	public WebDriver getDriver() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("--no-sandbox");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}
	
	public static void main(String[] args) {
		WebDriver driver = new Chrome().getDriver();
		driver.get("https://www.google.com");
		driver.close();
	}
}
