'use strict';

class TelluriumAnnouncementElement extends TelluriumElement {

	constructor() {
		super();
		this.templateDocument = this.getImported('/static/html/tellurium-announcement.html');
		this.initializeTemplate();
		this.startListeners();
	}

	initializeTemplate() {
		this.attachShadow({ mode: 'open' });
		let template = this.templateDocument.querySelector('template');
		let content = template.content.cloneNode(true);
		this.shadowRoot.appendChild(content);
	}

	startListeners() {
		Tellurium.messenger.subscribe('route-not-found', () => {
			this.showError('#route-not-found')
		});
		Tellurium.messenger.subscribe('route-match', () => {
			this.shadowRoot.querySelector('#last-announcement').textContent = '';
			this.shadowRoot.querySelector('#container').classList.remove('success');
			this.shadowRoot.querySelector('#container').classList.remove('error');
		});
		this.shadowRoot.querySelector('#collapse').addEventListener('click', () => {
			this.shadowRoot.querySelector('#container').classList.add('collapsed');
			this.shadowRoot.querySelector('#container').classList.remove('expanded');
		});
		this.shadowRoot.querySelector('#expand').addEventListener('click', () => {
			this.shadowRoot.querySelector('#container').classList.add('expanded');
			this.shadowRoot.querySelector('#container').classList.remove('collapsed');
		});
		this.shadowRoot.querySelector('#clear').addEventListener('click', () => {
			this.shadowRoot.querySelector('#last-announcement').textContent = '';
			this.shadowRoot.querySelector('#announcements').innerHTML = '';
			this.shadowRoot.querySelector('#container').classList.remove('success');
			this.shadowRoot.querySelector('#container').classList.remove('error');
		});
		this.shadowRoot.querySelector('#close').addEventListener('click', () => {
			this.shadowRoot.querySelector('#container').classList.add('closed');
		});
	}

	showError(templateSelector) {
		this.show(templateSelector, 'error');
		this.shadowRoot.querySelector('#container').classList.remove('closed');
		this.shadowRoot.querySelector('#container').classList.remove('success');
		this.shadowRoot.querySelector('#container').classList.add('error');
	}

	showSuccess(templateSelector) {
		this.show(templateSelector, 'success');
		this.shadowRoot.querySelector('#container').classList.remove('closed');
		this.shadowRoot.querySelector('#container').classList.remove('error');
		this.shadowRoot.querySelector('#container').classList.add('success');
	}

	show(templateSelector, messageType) {
		let announcementsElement = this.shadowRoot.querySelector('#announcements');
		let announcementTemplate = this.templateDocument.querySelector(templateSelector).content.cloneNode(true);
		if (this.isEmpty()) {
			announcementsElement.appendChild(announcementTemplate);
		} else {
			announcementsElement.insertBefore(announcementTemplate, announcementsElement.firstChild);
		}
		let announcementElement = this.shadowRoot.querySelector('#announcements li:first-child');
		announcementElement.classList.add(messageType);
		this.shadowRoot.querySelector('#last-announcement').textContent = announcementElement.textContent;
	}

	isEmpty() {
		console.log(this.shadowRoot.querySelectorAll('#container ul li').length);
		return this.shadowRoot.querySelectorAll('#container ul li').length === 0;
	}

}

window.customElements.define('tellurium-announcement', TelluriumAnnouncementElement);
