'use strict';

class TelluriumAnnouncementElement extends TelluriumElement {

	constructor() {
		super();
		this.announcements = 0;
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
			this.showError('template#route-not-found')
		});
		this.shadowRoot.querySelector('button#collapse').addEventListener('click', () => {
			this.shadowRoot.querySelector('div#container').classList.add('collapsed');
			this.shadowRoot.querySelector('div#container').classList.remove('expanded');
		});
		this.shadowRoot.querySelector('button#expand').addEventListener('click', () => {
			this.shadowRoot.querySelector('div#container').classList.add('expanded');
			this.shadowRoot.querySelector('div#container').classList.remove('collapsed');
		});
		this.shadowRoot.querySelector('button#clear').addEventListener('click', () => {
			if (this.announcements > 0) {
				let containerElement = this.shadowRoot.querySelector('div#container');
				let announcementsElement = this.shadowRoot.querySelector('div#container ul');
				let lastAnnouncementElement = this.shadowRoot.querySelector('div#container p');
				containerElement.removeChild(announcementsElement);
				containerElement.removeChild(lastAnnouncementElement);
				this.announcements = 0;
			}
		});
		this.shadowRoot.querySelector('button#close').addEventListener('click', () => {
			this.shadowRoot.querySelector('div#container').classList.add('closed');
		});
	}

	showError(templateSelector) {
		if (this.announcements === 0) {
			let announcementsTemplate = this.templateDocument.querySelector('template#announcements').content.cloneNode(true);
			let lastAnnouncementTemplate = this.templateDocument.querySelector('template#last-announcement').content.cloneNode(true);
			this.shadowRoot.querySelector('div#container').appendChild(lastAnnouncementTemplate);
			this.shadowRoot.querySelector('div#container').appendChild(announcementsTemplate);
		}
		let containerElement = this.shadowRoot.querySelector('div#container');
		let announcementsElement = this.shadowRoot.querySelector('div#container ul');
		let lastAnnouncementElement = this.shadowRoot.querySelector('div#container p');
		let announcementTemplate = this.templateDocument.querySelector(templateSelector).content.cloneNode(true);
		if (this.announcements > 0) {
			announcementsElement.insertBefore(announcementTemplate, announcementsElement.firstChild);
		} else {
			announcementsElement.appendChild(announcementTemplate);
		}
		let announcementElement = this.shadowRoot.querySelector('div#container ul li:first-child');
		lastAnnouncementElement.textContent = announcementElement.textContent;
		containerElement.classList.remove('success');
		lastAnnouncementElement.classList.remove('success');
		containerElement.classList.add('error');
		lastAnnouncementElement.classList.add('error');
		containerElement.classList.remove('closed');
		this.announcements++;
	}

}

window.customElements.define('tellurium-announcement', TelluriumAnnouncementElement);
