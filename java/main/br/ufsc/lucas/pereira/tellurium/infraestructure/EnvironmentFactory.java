package br.ufsc.lucas.pereira.tellurium.infraestructure;

import org.glassfish.hk2.api.Factory;

import br.ufsc.lucas.pereira.tellurium.Environment;
import br.ufsc.lucas.pereira.tellurium.DevelopmentEnvironment;

public class EnvironmentFactory implements Factory<Environment> {

	@Override
	public void dispose(Environment environment) {}

	@Override
	public Environment provide() {
		return new DevelopmentEnvironment();
	}

}
