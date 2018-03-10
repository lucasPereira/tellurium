package br.ufsc.lucas.pereira.tellurium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufsc.lucas.pereira.tellurium.browsing.Browser;

public class ExempleTest {

	private Browser tellurium;

	@Before
	public void setup() throws Exception {
		tellurium = new Browser();
	}

	@Test
	public void test() throws Exception {
		tellurium.goTo("http://setic.ufsc.br");
		tellurium.assertText("#portal-title", "Superintendência de Governança Eletrônica e Tecnologia da Informação e Comunicação");
	}

	@After
	public void teardown() throws Exception {
		tellurium.fechar();
	}

}
