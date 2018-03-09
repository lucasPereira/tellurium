package br.ufsc.lucas.pereira.tellurium.suporte;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class FormatadorDeLog extends Formatter {

	@Override
	public String format(LogRecord registro) {
		Long sequencia = registro.getSequenceNumber();
		String nivel = registro.getLevel().getLocalizedName();
		String mensagem = registro.getMessage();
		if (registro.getThrown() != null) {
			StringWriter escritorDeString = new StringWriter();
			PrintWriter escritorDeImpressao = new PrintWriter(escritorDeString);
			registro.getThrown().printStackTrace(escritorDeImpressao);
			escritorDeImpressao.close();
			String mensagemDeExcecao = escritorDeString.toString();
			return String.format("[%2$s] %3$s %4$s", sequencia, nivel, mensagem, mensagemDeExcecao);
		}
		return String.format("[%2$s] %3$s\n", sequencia, nivel, mensagem);
	}

}
