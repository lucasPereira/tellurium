package br.ufsc.lucas.pereira.tellurium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufsc.lucas.pereira.tellurium.Navegante;

public class TesteExemplo {

	private Navegante tellurium;

	@Before
	public void configurar() throws Exception {
		tellurium = new Navegante();
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
