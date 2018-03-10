package br.ufsc.lucas.pereira.tellurium.infraestructure;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.logging.LoggingFeature.Verbosity;
import org.glassfish.jersey.server.ResourceConfig;

import br.ufsc.lucas.pereira.tellurium.Environment;

public class Server {

	private HttpServer server;
	private Environment environment;

	public Server(Environment environment) {
		this.environment = environment;
		URI uri = UriBuilder.fromUri(environment.baseUri()).build();
		ResourceConfig configuration = new ResourceConfig();
		configuration.packages(environment.resourcesPackage());
		configuration.register(new LogFIlter());
		configuration.register(new EnvironmentBinder(environment));
		configuration.register(new LoggingFeature(environment.services().logger(), environment.jerseyLogLevel(), Verbosity.HEADERS_ONLY, LoggingFeature.DEFAULT_MAX_ENTITY_SIZE));
		configuration.register(new ExceptionHandler());
		configuration.register(new WebExceptionHandler());
		server = GrizzlyHttpServerFactory.createHttpServer(uri, configuration, false);
	}

	public void start() throws IOException {
		server.start();
		environment.services().logger().info(environment.baseUri());
	}

	public void shutdown() {
		server.shutdown();
	}

}
