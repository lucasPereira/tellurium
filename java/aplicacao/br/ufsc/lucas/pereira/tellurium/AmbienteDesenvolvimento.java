package br.ufsc.lucas.pereira.tellurium;

import java.io.File;
import java.util.logging.Level;

public class AmbienteDesenvolvimento implements Ambiente {

	private ServicosDesenvolvimento servicos;

	public AmbienteDesenvolvimento() {
		servicos = new ServicosDesenvolvimento(this);
	}

	@Override
	public Servicos servicos() {
		return servicos;
	}

	@Override
	public String enderecoBase() {
		return "http://localhost:7000";
	}

	@Override
	public String pacoteDeRecursos() {
		return "br.ufsc.lucas.pereira.tellurium.recursos";
	}

	@Override
	public Level nivelDeLog() {
		return Level.FINER;
	}

	@Override
	public Level nivelDosLogsDoJersey() {
		return Level.FINEST;
	}

	@Override
	public String caminhoDosArquivosEstaticos() {
		return "estatico";
	}

	@Override
	public String caminhoDaPaginaInicial() {
		return new File(caminhoDosArquivosEstaticos(), "index.html").getPath();
	}

}
