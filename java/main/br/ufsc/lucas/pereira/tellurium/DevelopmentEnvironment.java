package br.ufsc.lucas.pereira.tellurium;

import java.io.File;
import java.util.logging.Level;

public class DevelopmentEnvironment implements Environment {

	private DevelopmentServices services;

	public DevelopmentEnvironment() {
		services = new DevelopmentServices(this);
	}

	@Override
	public Services services() {
		return services;
	}

	@Override
	public String baseUri() {
		return "http://localhost:7000";
	}

	@Override
	public String resourcesPackage() {
		return "br.ufsc.lucas.pereira.tellurium.resources";
	}

	@Override
	public Level logLevel() {
		return Level.FINER;
	}

	@Override
	public Level jerseyLogLevel() {
		return Level.FINEST;
	}

	@Override
	public String staticFolder() {
		return "static";
	}

	@Override
	public String indexPage() {
		return new File(staticFolder(), "index.html").getPath();
	}

}
