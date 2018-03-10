package br.ufsc.lucas.pereira.tellurium.infraestructure;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import br.ufsc.lucas.pereira.tellurium.Environment;

public class EnvironmentBinder extends AbstractBinder {

	private Environment environment;

	public EnvironmentBinder(Environment environment) {
		this.environment = environment;
	}

	@Override
	protected void configure() {
		bind(environment).to(Environment.class);
	}

}
