package br.ufsc.lucas.pereira.tellurium.teste;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteExemplo {

	@Test
	public void testar() throws Exception {
		System.setProperty("webdriver.gecko.driver", "drivers/geckodriver-linux64-0.18");
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver-linux64-2.32");
		WebDriver selenium = new ChromeDriver();
		selenium.navigate().to("https://google.com.br");
		selenium.close();
	}

}
