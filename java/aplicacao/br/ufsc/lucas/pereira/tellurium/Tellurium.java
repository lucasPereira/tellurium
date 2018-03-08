package br.ufsc.lucas.pereira.tellurium;

import java.io.IOException;

import br.ufsc.lucas.pereira.tellurium.infraestrutura.Servidor;

public class Tellurium {

	public static void main(String[] args) throws IOException {
		Ambiente ambiente = new AmbienteDesenvolvimento();
		Servidor servidor = new Servidor(ambiente);
		servidor.iniciar();
		System.in.read();
		servidor.terminar();
	}

}
