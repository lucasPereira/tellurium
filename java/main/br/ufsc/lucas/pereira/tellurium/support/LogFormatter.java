package br.ufsc.lucas.pereira.tellurium.support;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		String level = record.getLevel().getLocalizedName();
		String message = record.getMessage();
		if (record.getThrown() != null) {
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			record.getThrown().printStackTrace(printWriter);
			printWriter.close();
			String exceptionMessage = stringWriter.toString();
			return String.format("[%s] %s %s", level, message, exceptionMessage);
		}
		return String.format("[%s] %s\n", level, message);
	}

}
