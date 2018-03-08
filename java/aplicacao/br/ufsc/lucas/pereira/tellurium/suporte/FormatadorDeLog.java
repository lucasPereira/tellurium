package br.ufsc.lucas.pereira.tellurium.suporte;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class FormatadorDeLog extends Formatter {

	@Override
	public String format(LogRecord record) {
		Long sequencia = record.getSequenceNumber();
		String nivel = record.getLevel().getLocalizedName();
		String mensagem = record.getMessage();
		return String.format("[%2$s] %3$s\n", sequencia, nivel, mensagem);
	}

}
