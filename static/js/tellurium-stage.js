'use strict';

class TelluriumStageElement extends TelluriumElement {

	constructor() {
		super();
		this.attachShadow({ mode: 'open' });
		let elementDocument = document.currentScript.ownerDocument;
		let template = elementDocument.querySelector('template');
		Tellurium.messenger.subscribe('page-change', (page) => {
			this.clearShadow();
			let content = template.content.cloneNode(true);
			let link = content.querySelector('link');
			this.appendChild(link);
			link.addEventListener('load', () => {
				let linkDocument = link.import;
				linkDocument.body.childNodes.forEach((node) => this.shadowRoot.appendChild(node.cloneNode(true)));
				this.removeChild(link);
			});
			link.href = page;
		});
	}

	clearShadow() {
		while (this.shadowRoot.hasChildNodes()) {
			this.shadowRoot.removeChild(this.shadowRoot.firstChild);
		}
	}

}

window.customElements.define('tellurium-stage', TelluriumStageElement);
