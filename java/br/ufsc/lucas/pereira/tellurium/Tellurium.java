package br.ufsc.lucas.pereira.tellurium;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class Tellurium {

	private WebDriver driver;

	public Tellurium() {
		System.setProperty("webdriver.gecko.driver", "drivers/geckodriver-linux64-0.18");
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver-linux64-2.32");
		driver = new FirefoxDriver();
	}

	public void irPara(String uri) {
		driver.navigate().to(uri);
	}

	public void fechar() {
		driver.quit();
	}

	public void assegureTexto(String seletor, String textoEsperado) {
		WebElement elemento = driver.findElement(By.cssSelector(seletor));
		Assert.assertEquals(textoEsperado, elemento.getText());
	}

}
