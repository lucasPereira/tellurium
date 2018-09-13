'use strict'

class TelluriumButtonIconElement extends TelluriumElement {

	constructor() {
		super();
		this.attachShadow({ mode: 'open' });
		let imported = this.getImported('/static/html/tellurium-button-icon.html');
		let template = imported.querySelector('template');
		let content = template.content.cloneNode(true);
		this.shadowRoot.appendChild(content);
		this.shadowRoot.querySelector('button').setAttribute('title', this.getAttribute('title'));
		this.shadowRoot.querySelector('i').textContent = this.textContent;
	}

}

window.customElements.define('tellurium-button-icon', TelluriumButtonIconElement);
