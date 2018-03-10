'use strict';

class Topic {

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

class Messenger {

	constructor() {
		this.topics = {};
	}

	createTopic(name) {
		if (this.topics[name]) {
			console.warn(`Topic ${name} already exists.`);
			return;
		}
		console.info(`${name} topic created.`);
		this.topics[name] = new Topic();
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
		console.info(`Publishing ${message} in ${topic}.`);
		this.topics[topic].publish(message);
	}

}

class TelluriumElement extends HTMLElement {

	constructor() {
		super();
	}

}

var Tellurium = {
	messenger: new Messenger()
};
