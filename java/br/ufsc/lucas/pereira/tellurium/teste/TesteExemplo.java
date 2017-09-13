package br.ufsc.lucas.pereira.tellurium.teste;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufsc.lucas.pereira.tellurium.Tellurium;

public class TesteExemplo {

	private Tellurium tellurium;

	@Before
	public void configurar() throws Exception {
		tellurium = new Tellurium();
	}

	@Test
	public void testar() throws Exception {
		tellurium.irPara("http://setic.ufsc.br");
		tellurium.assegureTexto("#portal-title", "Superintendência de Governança Eletrônica e Tecnologia da Informação e Comunicação");
	}

	@After
	public void finalizar() throws Exception {
		tellurium.fechar();
	}

}
