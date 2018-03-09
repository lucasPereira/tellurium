package br.ufsc.lucas.pereira.tellurium.recursos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import br.ufsc.lucas.pereira.tellurium.Ambiente;

@Path("/estatico/{caminho: .*}")
public class RecursoEstaticos {

	@Inject
	private Ambiente ambiente;

	@GET
	public Response obter(@PathParam("caminho") String caminho) throws IOException {
		File arquivo = new File(ambiente.caminhoDosArquivosEstaticos(), caminho);
		if (!arquivo.exists()) {
			throw new WebApplicationException(404);
		}
		String tipoDeMidia = Files.probeContentType(arquivo.toPath());
		return Response.status(200).type(tipoDeMidia).entity(arquivo).build();
	}

}
