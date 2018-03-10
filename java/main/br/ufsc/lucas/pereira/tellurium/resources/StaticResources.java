package br.ufsc.lucas.pereira.tellurium.resources;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import br.ufsc.lucas.pereira.tellurium.Environment;

@Path("/static/{path: .*}")
public class StaticResources {

	@Inject
	private Environment environment;

	@GET
	public Response get(@PathParam("path") String path) throws IOException {
		File file = new File(environment.staticFolder(), path);
		if (!file.exists()) {
			throw new WebApplicationException(404);
		}
		String mediaType = Files.probeContentType(file.toPath());
		return Response.status(200).type(mediaType).entity(file).build();
	}

}
