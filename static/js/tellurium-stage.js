'use strict';

class TelluriumStageElement extends TelluriumElement {

	constructor() {
		super();
		let imported = this.getImported('/static/html/tellurium-stage.html');
		let shadow = this.attachShadow({ mode: 'open' });
		let template = imported.querySelector('template');
		let content = template.content.cloneNode(true);
		Tellurium.messenger.subscribe('route-match', (message) => {
			this.clearShadow();
			this.load(message.page);
		});
		Tellurium.messenger.subscribe('route-not-found', () => {
			this.clearShadow();
		});
		shadow.appendChild(content);
	}

	load(page) {
		let imported = this.getImported(page);
		let container = this.shadowRoot.querySelector('div');
		imported.body.childNodes.forEach((node) => {
			container.appendChild(node.cloneNode(true));
		});
	}

	clearShadow() {
		let container = this.shadowRoot.querySelector('div');
		while (container.firstChild) {
			container.removeChild(container.firstChild);
		}
	}

}

window.customElements.define('tellurium-stage', TelluriumStageElement);
