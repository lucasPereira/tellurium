'use strict';

class TelluriumAnnouncementElement extends TelluriumElement {

	constructor() {
		super();
		this.open = true;
		let shadow = this.attachShadow({ mode: 'open' });
		let elementDocument = document.currentScript.ownerDocument;
		let template = elementDocument.querySelector('template');
		let content = template.content.cloneNode(true);
		shadow.appendChild(content);
		Tellurium.messenger.subscribe('route-not-found', () => {
			let templateRouteNotFound = elementDocument.querySelector('template#route-not-found');
			let contentRouteNotFound = templateRouteNotFound.content.cloneNode(true);
			shadow.querySelector('p').textContent = contentRouteNotFound.textContent;
			shadow.querySelector('ul').appendChild(contentRouteNotFound);
		});
		shadow.querySelector('button').addEventListener('click', () => {
			if (this.open) {
				shadow.querySelector('ul').style.display = 'none';
			} else {
				shadow.querySelector('ul').style.display = 'block';
			}
			this.open = !this.open;
		});
	}

}

window.customElements.define('tellurium-announcement', TelluriumAnnouncementElement);
