'use strict';

class TelluriumTopic {

	constructor() {
		this.subscribers = [];
		this.messages = [];
	}

	subscribe(handler) {
		this.subscribers.push(handler);
		this.messages.forEach((message) => {
			handler(message);
		});
	}

	publish(message) {
		this.messages.push(message);
		this.subscribers.forEach((handler) => {
			handler(message);
		});
	}

}

class TelluriumMessenger {

	constructor() {
		this.topics = {};
		this.createTopic('uri-change');
		this.createTopic('route-match');
		this.createTopic('route-not-found');
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
		messenger.subscribe('uri-change', (message) => {
			if (!message.popstate) {
				window.history.pushState(message, document.title, message.uri);
			}
		});
		window.addEventListener('popstate', (event) => {
			let message = event.state;
			message.popstate = true;
			messenger.publish('uri-change', message);
		});
	}

}

class TelluriumRouter {

	constructor(messenger) {
		this.messenger = messenger;
		this.routes = [{
			pattern: '/',
			page: '/static/html/page-index.html'
		}, {
			pattern: '/user/(?<id>.+)',
			page: '/static/html/page-test.html'
		}, {
			pattern: '/category/(?<category>.+)/user/(?<user>.+)',
			page: '/static/html/page-test.html'
		}, {
			pattern: '/not-imported',
			page: '/static/html/not-imported.html'
		}];
		messenger.subscribe('uri-change', (message) => {
			this.match(message);
		});
		this.match({ uri: window.location.pathname });
	}

	match(message) {
		let uri = message.uri;
		let matched = false;
		this.routes.forEach((route) => {
			let regex = new RegExp(route.pattern);
			let result = regex.exec(uri);
			if (result && result[0] == uri) {
				let match = { uri, page: route.page, parameters: result.groups };
				this.messenger.publish('route-match', match);
				this.currentMatch = match;
				matched = true;
			}
		});
		if (!matched) {
			this.messenger.publish('route-not-found', message);
		}
	}

}

class TelluriumElement extends HTMLElement {

	constructor() {
		super();
	}

	getImported(uri) {
		let seletor = `link[rel="import"][href="${uri}"]`;
		let imported = document.querySelector(seletor);
		return imported.import;
	}

}

var Tellurium = {}
Tellurium.messenger = new TelluriumMessenger();
Tellurium.history = new TelluriumHistory(Tellurium.messenger);
Tellurium.router = new TelluriumRouter(Tellurium.messenger);
