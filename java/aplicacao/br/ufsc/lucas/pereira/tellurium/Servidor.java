package br.ufsc.lucas.pereira.tellurium;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Servidor {

	private HttpServer servidor;

	public Servidor(Configuracoes configuracoes) {
		ResourceConfig configuracaoDeRecurso = new ResourceConfig();
		configuracaoDeRecurso.packages(configuracoes.obterPacoteDeRecursos());
		URI uri = UriBuilder.fromUri(configuracoes.obterEnderecoBase()).build();
		servidor = GrizzlyHttpServerFactory.createHttpServer(uri, configuracaoDeRecurso, false);
	}

	public void iniciar() throws IOException {
		servidor.start();
	}

	public void terminar() {
		servidor.shutdown();
	}

}
