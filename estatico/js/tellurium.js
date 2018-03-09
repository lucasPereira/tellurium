'use strict';

class ElementoTellurium extends HTMLElement {

	constructor(template) {
		super();
	}

}


class TelluriumEnlace extends ElementoTellurium {

	constructor() {
		super('/estatico/html/tellurium-enlace.html');
	}

}
window.customElements.define('tellurium-enlace', TelluriumEnlace);
