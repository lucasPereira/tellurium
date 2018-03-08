package br.ufsc.lucas.pereira.tellurium.infraestrutura;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.UriBuilder;

import br.ufsc.lucas.pereira.tellurium.Ambiente;

public class FiltroDeLog implements ContainerRequestFilter, ContainerResponseFilter {

	@Inject
	private Ambiente ambiente;

	@Override
	public void filter(ContainerRequestContext requisicao) throws IOException {
		String mensagem = String.format("%s %s", requisicao.getMethod(), obterUri(requisicao));
		ambiente.servicos().logger().fine(mensagem);
	}

	@Override
	public void filter(ContainerRequestContext requisicao, ContainerResponseContext resposta) throws IOException {
		String mensagem = String.format("%s %s %d", requisicao.getMethod(), obterUri(requisicao), resposta.getStatus());
		ambiente.servicos().logger().fine(mensagem);
	}

	private String obterUri(ContainerRequestContext requisicao) {
		UriBuilder uri = UriBuilder.fromPath(requisicao.getUriInfo().getPath());
		requisicao.getUriInfo().getQueryParameters().forEach((chave, valores) -> {
			valores.forEach(valor -> {
				uri.queryParam(chave, valor);
			});
		});
		return uri.build().toString();
	}

}
