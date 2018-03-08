package br.ufsc.lucas.pereira.tellurium.infraestrutura;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import br.ufsc.lucas.pereira.tellurium.Ambiente;

public class LigadorDeAmbiente extends AbstractBinder {

	private Ambiente ambiente;

	public LigadorDeAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
	}

	@Override
	protected void configure() {
		bind(ambiente).to(Ambiente.class);
	}

}
