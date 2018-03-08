package br.ufsc.lucas.pereira.tellurium.infraestrutura;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.logging.LoggingFeature.Verbosity;
import org.glassfish.jersey.server.ResourceConfig;

import br.ufsc.lucas.pereira.tellurium.Ambiente;

public class Servidor {

	private HttpServer servidor;

	public Servidor(Ambiente ambiente) {
		ResourceConfig configuracaoDeRecurso = new ResourceConfig();
		configuracaoDeRecurso.packages(ambiente.pacoteDeRecursos());
		configuracaoDeRecurso.register(new FiltroDeLog());
		configuracaoDeRecurso.register(new LigadorDeAmbiente(ambiente));
		configuracaoDeRecurso.register(new LoggingFeature(ambiente.servicos().logger(), ambiente.nivelDosLogsDoJersey(), Verbosity.HEADERS_ONLY, LoggingFeature.DEFAULT_MAX_ENTITY_SIZE));
		URI uri = UriBuilder.fromUri(ambiente.enderecoBase()).build();
		servidor = GrizzlyHttpServerFactory.createHttpServer(uri, configuracaoDeRecurso, false);
	}

	public void iniciar() throws IOException {
		servidor.start();
	}

	public void terminar() {
		servidor.shutdown();
	}

}
