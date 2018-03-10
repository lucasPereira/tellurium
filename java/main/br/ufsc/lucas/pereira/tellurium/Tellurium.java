package br.ufsc.lucas.pereira.tellurium;

import java.io.IOException;

import br.ufsc.lucas.pereira.tellurium.infraestructure.Server;

public class Tellurium {

	public static void main(String[] args) throws IOException {
		Environment environment = new DevelopmentEnvironment();
		Server servidor = new Server(environment);
		servidor.start();
		System.in.read();
		servidor.shutdown();
	}

}
