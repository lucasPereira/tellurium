package br.ufsc.lucas.pereira.tellurium.infraestructure;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import br.ufsc.lucas.pereira.tellurium.Environment;

public class FactoryEnvironmentBinder extends AbstractBinder {

	@Override
	protected void configure() {
		bindFactory(EnvironmentFactory.class).to(Environment.class);
	}

}
