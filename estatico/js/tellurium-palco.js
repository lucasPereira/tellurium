'use strict';

class TelluriumPalco extends ElementoTellurium {
	constructor() {
		super();
		Tellurium.mensageiro.ouvir('mudanca-de-pagina', (pagina) => {
			this.primeiroFilho.textContent = pagina;
		});
	}
}

window.customElements.define('tellurium-palco', TelluriumPalco);
