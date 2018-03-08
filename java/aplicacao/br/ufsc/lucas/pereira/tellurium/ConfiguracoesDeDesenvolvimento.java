package br.ufsc.lucas.pereira.tellurium;

public class ConfiguracoesDeDesenvolvimento implements Configuracoes {

	@Override
	public String obterEnderecoBase() {
		return "http://localhost:7000";
	}

	@Override
	public String obterPacoteDeRecursos() {
		return "br.ufsc.lucas.pereira.tellurium";
	}

}
