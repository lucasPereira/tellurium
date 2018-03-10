'use strict';

class TelluriumLinkElement extends TelluriumElement {

	constructor() {
		super();
		let shadow = this.attachShadow({ mode: 'open' });
		let elementDocument = document.currentScript.ownerDocument;
		let template = elementDocument.querySelector('template');
		let content = template.content.cloneNode(true);
		let link = content.querySelector('a');
		link.textContent = this.textContent;
		link.setAttribute('href', this.getAttribute('uri'));
		link.addEventListener('click', (event) => {
			event.preventDefault();
			Tellurium.messenger.publish('page-change', { uri: this.getAttribute('uri'), page: this.getAttribute('page') });
		});
		shadow.appendChild(content);
	}

}

window.customElements.define('tellurium-link', TelluriumLinkElement);
