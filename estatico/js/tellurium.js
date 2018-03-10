'use strict';

class Topico {

	constructor() {
		this.tratadores = [];
	}

	inscrever(tratador) {
		this.tratadores.push(tratador);
	}

	divulgar(mensagem) {
		this.tratadores.forEach((tratador) => {
			tratador(mensagem);
		});
	}

}

class Mensageiro {

	constructor() {
		this.topicos = {};
	}

	criarTopico(nome) {
		if (this.topicos[nome]) {
			console.warn(`Tópico ${nome} já existente.`);
			return;
		}
		console.info(`Criando tópico ${nome}.`);
		this.topicos[nome] = new Topico();
	}

	ouvir(topico, tratador) {
		window.addEventListener('load', () => {
			if (!this.topicos[topico]) {
				console.warn(`Tópico ${topico} inexistente.`);
				return;
			}
			this.topicos[topico].inscrever(tratador);
		});
	}

	divulgar(topico, mensagem) {
		if (!this.topicos[topico]) {
			console.warn(`Tópico ${topico} inexistente.`);
			return;
		}
		console.info(`Divulgando ${mensagem} em ${topico}.`);
		this.topicos[topico].divulgar(mensagem);
	}

}

class ElementoTellurium extends HTMLElement {

	constructor() {
		super();
		this.raiz = this.attachShadow({ mode: 'open' });
		this.template = document.currentScript.ownerDocument.querySelector('template');
		this.conteudo = this.template.content.cloneNode(true);
		this.raiz.appendChild(this.conteudo);
	}

	get primeiroFilho() {
		return this.raiz.firstElementChild;
	}

}

var Tellurium = {
	mensageiro: new Mensageiro()
};
