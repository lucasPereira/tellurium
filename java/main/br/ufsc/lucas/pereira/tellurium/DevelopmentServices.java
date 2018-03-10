package br.ufsc.lucas.pereira.tellurium;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import br.ufsc.lucas.pereira.tellurium.support.LogFormatter;

public class DevelopmentServices implements Services {

	public DevelopmentServices(Environment environment) {
		buildLogger(environment);
	}

	private void buildLogger(Environment environment) {
		Handler handler = new ConsoleHandler();
		handler.setLevel(environment.logLevel());
		handler.setFormatter(new LogFormatter());
		Logger logger = Logger.getLogger(Tellurium.class.getName());
		logger.setLevel(environment.logLevel());
		logger.addHandler(handler);
		Logger rootLogger = Logger.getGlobal().getParent();
		rootLogger.removeHandler(rootLogger.getHandlers()[0]);
	}

	@Override
	public Logger logger() {
		return Logger.getLogger(Tellurium.class.getName());
	}

}
