package br.ufsc.lucas.pereira.tellurium.infraestructure;

import java.util.logging.Level;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.ufsc.lucas.pereira.tellurium.Environment;

@Provider
public class ExceptionHandler implements ExceptionMapper<Throwable> {

	@Inject
	private Environment environment;

	@Override
	public Response toResponse(Throwable exception) {
		environment.services().logger().log(Level.SEVERE, "Server exception", exception);
		return Response.status(500).build();
	}

}
