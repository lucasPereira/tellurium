package br.ufsc.lucas.pereira.tellurium.recursos;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import br.ufsc.lucas.pereira.tellurium.Ambiente;

@Path("/estatico/{caminho: .*}")
public class RecursoEstaticos {

	@Inject
	private Ambiente ambiente;

	@GET
	public String obter(@PathParam("caminho") String caminho) {
		return ambiente.enderecoBase();
	}

}
