'use strict';

class TelluriumStageElement extends TelluriumElement {

	constructor() {
		super();
		Tellurium.messenger.subscribe('page-change', (page) => {
			this.root.firstElementChild.textContent = page;
		});
	}

}

window.customElements.define('tellurium-stage', TelluriumStageElement);
