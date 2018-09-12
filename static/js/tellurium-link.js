'use strict';

class TelluriumLinkElement extends TelluriumElement {

	constructor() {
		super();
		let imported = this.getImported('/static/html/tellurium-link.html');
		let shadow = this.attachShadow({ mode: 'closed' });
		let template = imported.querySelector('template');
		let content = template.content.cloneNode(true);
		let link = content.querySelector('a');
		link.textContent = this.textContent;
		link.setAttribute('href', this.getAttribute('uri'));
		link.addEventListener('click', (event) => {
			event.preventDefault();
			Tellurium.messenger.publish('uri-change', { uri: this.getAttribute('uri') });
		});
		shadow.appendChild(content);
	}

}

window.customElements.define('tellurium-link', TelluriumLinkElement);
