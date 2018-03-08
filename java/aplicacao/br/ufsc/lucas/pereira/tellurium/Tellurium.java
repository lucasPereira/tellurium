package br.ufsc.lucas.pereira.tellurium;

import java.io.IOException;

public class Tellurium {

	public static void main(String[] args) throws IOException {
		Configuracoes configuracoes = new ConfiguracoesDeDesenvolvimento();
		Servidor servidor = new Servidor(configuracoes);
		servidor.iniciar();
		System.in.read();
		servidor.terminar();
	}

}
