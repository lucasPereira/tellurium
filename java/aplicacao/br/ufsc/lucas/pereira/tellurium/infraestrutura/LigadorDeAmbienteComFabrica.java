package br.ufsc.lucas.pereira.tellurium.infraestrutura;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import br.ufsc.lucas.pereira.tellurium.Ambiente;

public class LigadorDeAmbienteComFabrica extends AbstractBinder {

	@Override
	protected void configure() {
		bindFactory(FabricaDeAmbiente.class).to(Ambiente.class);
	}

}
