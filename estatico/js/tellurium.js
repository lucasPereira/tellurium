'use strict';

class ElementoTellurium extends HTMLElement {

	constructor() {
		super();
		this.raiz = this.attachShadow({ mode: 'open' });
		this.template = document.currentScript.ownerDocument.querySelector('template');
		this.conteudo = this.template.content.cloneNode(true);
		this.raiz.appendChild(this.conteudo);
		console.log(this);
		console.log(this.template);
		console.log(this.raiz);
		console.log(this.conteudo);
	}

	get primeiroFilho() {
		return this.raiz.firstElementChild;
	}

}
