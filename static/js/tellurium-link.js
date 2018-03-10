'use strict';

class TelluriumLinkElement extends TelluriumElement {

	constructor() {
		super();
		this.root.firstElementChild.textContent = this.textContent;
		this.root.firstElementChild.setAttribute('href', this.getAttribute('uri'));
		this.root.firstElementChild.addEventListener('click', (event) => {
			event.preventDefault();
			Tellurium.messenger.publish('page-change', this.getAttribute('page'));
		});
	}

}

Tellurium.messenger.createTopic('page-change');
window.customElements.define('tellurium-link', TelluriumLinkElement);
