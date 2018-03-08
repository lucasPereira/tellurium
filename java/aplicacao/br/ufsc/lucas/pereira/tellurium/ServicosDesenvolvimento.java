package br.ufsc.lucas.pereira.tellurium;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import br.ufsc.lucas.pereira.tellurium.suporte.FormatadorDeLog;

public class ServicosDesenvolvimento implements Servicos {

	public ServicosDesenvolvimento(Ambiente ambiente) {
		construirLogger(ambiente);
	}

	private void construirLogger(Ambiente ambiente) {
		Handler console = new ConsoleHandler();
		console.setLevel(ambiente.nivelDeLog());
		console.setFormatter(new FormatadorDeLog());
		Logger logger = Logger.getLogger("tellurium");
		logger.setLevel(ambiente.nivelDeLog());
		logger.addHandler(console);
	}

	@Override
	public Logger logger() {
		return Logger.getLogger("tellurium");
	}

}
