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
	private Ambiente ambiente;

	public Servidor(Ambiente ambiente) {
		this.ambiente = ambiente;
		URI uri = UriBuilder.fromUri(ambiente.enderecoBase()).build();
		ResourceConfig configuracaoDeRecurso = new ResourceConfig();
		configuracaoDeRecurso.packages(ambiente.pacoteDeRecursos());
		configuracaoDeRecurso.register(new FiltroDeLog());
		configuracaoDeRecurso.register(new LigadorDeAmbiente(ambiente));
		configuracaoDeRecurso.register(new LoggingFeature(ambiente.servicos().logger(), ambiente.nivelDosLogsDoJersey(), Verbosity.HEADERS_ONLY, LoggingFeature.DEFAULT_MAX_ENTITY_SIZE));
		configuracaoDeRecurso.register(new TratadorDeExecao());
		configuracaoDeRecurso.register(new TratadorDeExecaoWeb());
		servidor = GrizzlyHttpServerFactory.createHttpServer(uri, configuracaoDeRecurso, false);
	}

	public void iniciar() throws IOException {
		servidor.start();
		ambiente.servicos().logger().info(ambiente.enderecoBase());
	}

	public void terminar() {
		servidor.shutdown();
	}

}
