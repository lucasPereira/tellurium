package br.ufsc.lucas.pereira.tellurium.infraestrutura;

import java.util.logging.Level;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.ufsc.lucas.pereira.tellurium.Ambiente;

@Provider
public class TratadorDeExecao implements ExceptionMapper<Throwable> {

	@Inject
	private Ambiente ambiente;

	@Override
	public Response toResponse(Throwable excecao) {
		ambiente.servicos().logger().log(Level.SEVERE, "Exceção no servidor", excecao);
		return Response.status(500).build();
	}

}
