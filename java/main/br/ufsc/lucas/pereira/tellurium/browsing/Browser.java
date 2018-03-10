package br.ufsc.lucas.pereira.tellurium.browsing;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class Browser {

	private WebDriver driver;

	public Browser() {
		System.setProperty("webdriver.gecko.driver", "drivers/geckodriver-linux64-0.18");
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver-linux64-2.32");
		driver = new FirefoxDriver();
	}

	public void goTo(String uri) {
		driver.navigate().to(uri);
	}

	public void fechar() {
		driver.quit();
	}

	public void assertText(String selector, String expected) {
		WebElement element = driver.findElement(By.cssSelector(selector));
		Assert.assertEquals(expected, element.getText());
	}

}
