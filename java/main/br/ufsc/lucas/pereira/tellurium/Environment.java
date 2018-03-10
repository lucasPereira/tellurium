package br.ufsc.lucas.pereira.tellurium;

import java.util.logging.Level;

public interface Environment {

	public Services services();

	public String baseUri();

	public String resourcesPackage();

	public Level logLevel();

	public Level jerseyLogLevel();

	public String staticFolder();

	public String indexPage();

}
