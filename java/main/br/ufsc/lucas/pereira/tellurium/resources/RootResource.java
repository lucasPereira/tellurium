package br.ufsc.lucas.pereira.tellurium.resources;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import br.ufsc.lucas.pereira.tellurium.Environment;

@Path("/")
public class RootResource {

	@Inject
	private Environment environment;

	@GET
	public Response get() throws IOException {
		File file = new File(environment.indexPage());
		String mediaType = Files.probeContentType(file.toPath());
		return Response.status(200).type(mediaType).entity(file ).build();
	}

}
