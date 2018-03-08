package br.ufsc.lucas.pereira.tellurium.infraestrutura;

import org.glassfish.hk2.api.Factory;

import br.ufsc.lucas.pereira.tellurium.Ambiente;
import br.ufsc.lucas.pereira.tellurium.AmbienteDesenvolvimento;

public class FabricaDeAmbiente implements Factory<Ambiente> {

	@Override
	public void dispose(Ambiente ambiente) {}

	@Override
	public Ambiente provide() {
		return new AmbienteDesenvolvimento();
	}

}
