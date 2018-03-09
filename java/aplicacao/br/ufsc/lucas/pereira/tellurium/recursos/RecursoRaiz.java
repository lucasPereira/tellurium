package br.ufsc.lucas.pereira.tellurium.recursos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import br.ufsc.lucas.pereira.tellurium.Ambiente;

@Path("/")
public class RecursoRaiz {

	@Inject
	private Ambiente ambiente;

	@GET
	public Response obter() throws IOException {
		File arquivo = new File(ambiente.caminhoDaPaginaInicial());
		String tipoDeMidia = Files.probeContentType(arquivo.toPath());
		return Response.status(200).type(tipoDeMidia).entity(arquivo ).build();
	}

}
