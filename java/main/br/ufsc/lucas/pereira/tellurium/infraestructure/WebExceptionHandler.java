package br.ufsc.lucas.pereira.tellurium.infraestructure;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.ufsc.lucas.pereira.tellurium.Environment;

@Provider
public class WebExceptionHandler implements ExceptionMapper<WebApplicationException> {

	@Inject
	private Environment environment;

	@Override
	public Response toResponse(WebApplicationException exception) {
		environment.services().logger().finer(exception.getMessage());
		Response exceptionResponse = exception.getResponse();
		Status entity = Status.fromStatusCode(exceptionResponse.getStatus());
		return Response.fromResponse(exceptionResponse).type("text/html").entity(entity.getReasonPhrase()).build();
	}

}
