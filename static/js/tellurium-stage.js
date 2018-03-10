'use strict';

class TelluriumStageElement extends TelluriumElement {

	constructor() {
		super();
		this.attachShadow({ mode: 'open' });
		Tellurium.messenger.subscribe('page-change', (event) => {
			this.clearShadow();
			this.load(event.page);
		});
	}

	load(page) {
		let link = document.querySelector(`link[href="${page}"]`);
		if (link && link.import) {
			link.import.body.childNodes.forEach((node) => this.shadowRoot.appendChild(node.cloneNode(true)));
		}
	}

	clearShadow() {
		while (this.shadowRoot.hasChildNodes()) {
			this.shadowRoot.removeChild(this.shadowRoot.firstChild);
		}
	}

}

window.customElements.define('tellurium-stage', TelluriumStageElement);
