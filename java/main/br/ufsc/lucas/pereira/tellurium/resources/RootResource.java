package br.ufsc.lucas.pereira.tellurium.resources;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.ufsc.lucas.pereira.tellurium.Environment;

@Path("/{path: .*}")
public class RootResource {

	@Inject
	private Environment environment;

	@GET
	@Produces("text/html")
	public Response get() throws IOException {
		File file = new File(environment.indexPage());
		return Response.status(200).entity(file ).build();
	}

}
