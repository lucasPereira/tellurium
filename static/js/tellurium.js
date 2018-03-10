'use strict';

class TelluriumTopic {

	constructor() {
		this.subscribers = [];
	}

	subscribe(handler) {
		this.subscribers.push(handler);
	}

	publish(message) {
		this.subscribers.forEach((handler) => {
			handler(message);
		});
	}

}

class TelluriumMessenger {

	constructor() {
		this.topics = {};
		this.createTopic('page-change');
	}

	createTopic(name) {
		if (this.topics[name]) {
			console.warn(`Topic ${name} already exists.`);
			return;
		}
		console.info(`${name} topic created.`);
		this.topics[name] = new TelluriumTopic();
	}

	subscribe(topic, handler) {
		window.addEventListener('load', () => {
			if (!this.topics[topic]) {
				console.warn(`${topic} topic does not exist.`);
				return;
			}
			this.topics[topic].subscribe(handler);
		});
	}

	publish(topic, message) {
		if (!this.topics[topic]) {
			console.warn(`${topic} does not exist.`);
			return;
		}
		console.info(`Publishing ${JSON.stringify(message)} in ${topic}.`);
		this.topics[topic].publish(message);
	}

}

class TelluriumHistory {

	constructor(messenger) {
		messenger.subscribe('page-change', (event) => {
			if (!event.popstate) {
				window.history.pushState(event, document.title, event.uri);
			}
		});
		window.addEventListener('popstate', (event) => {
			event.state.popstate = true;
			messenger.publish('page-change', event.state);
		});
	}

}

class TelluriumElement extends HTMLElement {

	constructor() {
		super();
	}

}

var Tellurium = {}
Tellurium.messenger = new TelluriumMessenger();
Tellurium.history = new TelluriumHistory(Tellurium.messenger);
