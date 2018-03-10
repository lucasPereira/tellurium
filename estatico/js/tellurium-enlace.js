'use strict';

class TelluriumEnlace extends ElementoTellurium {

	constructor() {
		super();
		this.primeiroFilho.textContent = this.textContent;
		this.primeiroFilho.setAttribute('href', this.getAttribute('uri'));
		this.primeiroFilho.addEventListener('click', (evento) => {
			event.preventDefault();
		});
	}

}

window.customElements.define('tellurium-enlace', TelluriumEnlace);
