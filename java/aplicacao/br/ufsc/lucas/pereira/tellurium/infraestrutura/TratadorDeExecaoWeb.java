package br.ufsc.lucas.pereira.tellurium.infraestrutura;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.ufsc.lucas.pereira.tellurium.Ambiente;

@Provider
public class TratadorDeExecaoWeb implements ExceptionMapper<WebApplicationException> {

	@Inject
	private Ambiente ambiente;

	@Override
	public Response toResponse(WebApplicationException excecao) {
		ambiente.servicos().logger().finer(excecao.getMessage());
		Response respostaDaExecao = excecao.getResponse();
		Status entidade = Status.fromStatusCode(respostaDaExecao.getStatus());
		return Response.fromResponse(respostaDaExecao).type("text/html").entity(entidade.getReasonPhrase()).build();
	}

}
