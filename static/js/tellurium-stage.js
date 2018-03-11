'use strict';

class TelluriumStageElement extends TelluriumElement {

	constructor() {
		super();
		this.attachShadow({ mode: 'open' });
		Tellurium.messenger.subscribe('route-match', (message) => {
			this.clearShadow();
			this.load(message.page);
		});
		Tellurium.messenger.subscribe('route-not-found', () => {
			this.clearShadow();
		});
		let route = Tellurium.router.currentMatch;
		if (route) {
			this.clearShadow();
			this.load(route.page);
		}
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
