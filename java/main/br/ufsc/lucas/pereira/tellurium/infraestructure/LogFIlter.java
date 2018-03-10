package br.ufsc.lucas.pereira.tellurium.infraestructure;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.UriBuilder;

import br.ufsc.lucas.pereira.tellurium.Environment;

public class LogFIlter implements ContainerRequestFilter, ContainerResponseFilter {

	@Inject
	private Environment environment;

	@Override
	public void filter(ContainerRequestContext request) throws IOException {
		String message = String.format("%s /%s", request.getMethod(), getUri(request));
		environment.services().logger().fine(message);
	}

	@Override
	public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
		String message = String.format("%s /%s %d", request.getMethod(), getUri(request), response.getStatus());
		environment.services().logger().fine(message);
	}

	private String getUri(ContainerRequestContext request) {
		UriBuilder uri = UriBuilder.fromPath(request.getUriInfo().getPath());
		request.getUriInfo().getQueryParameters().forEach((key, values) -> {
			values.forEach(value -> {
				uri.queryParam(key, value);
			});
		});
		return uri.build().toString();
	}

}
