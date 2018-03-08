package br.ufsc.lucas.pereira.tellurium;

import java.util.logging.Level;

public interface Ambiente {

	public Servicos servicos();

	public String enderecoBase();

	public String pacoteDeRecursos();

	public Level nivelDeLog();

	public Level nivelDosLogsDoJersey();

}
