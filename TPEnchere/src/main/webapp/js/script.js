"use strict";

class Script {
	
	constructor() {
		this.getTitle = location.pathname;
		this.lastIndex = this.getTitle.lastIndexOf('/');
		this.is_function(this.getTitle.slice(this.lastIndex + 1));
	}

	is_function(path) {
		if(path == "") {
			path = "ServletAccueil";
		}

		if({}.toString.call(this[path]) === '[object Function]') this[path]();
	}
	
	$(className) {
		return document.getElementsByClassName(className)[0];
	}

	ServletAccueil() {
		document.querySelectorAll('input[name="achatsVentes"]').forEach(f => {
			f.addEventListener(`change`, e => {
				/* TODO à corriger */
				/*e.target.parentElement.parentElement.parentElement.children.forEach(j => {
					console.dir(j.target)
					if(j.value == e.value) {
						console.log('ok');
					} else {
						j.parentElement.nextSibling.children.forEach(g => {
							g.disabled = true;
						});
					}
				})*/
			})
		})
	}
	
	ServletConnexion() {
		null;
		/**
			var password = document.getElementById("password")
  , confirm_password = document.getElementById("confirm_password");

function validatePassword(){
  if(password.value != confirm_password.value) {
    confirm_password.setCustomValidity("Passwords Don't Match");
  } else {
    confirm_password.setCustomValidity('');
  }
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;
		*/
	}
}

(() => {
	const script = new Script();
	
	if(script instanceof Object !== true) {
		console.error(`Le Script n'est pas un Objet`);
		void 0;
	} else {
		window.addEventListener("load", () => {}, { once: true });
	}
})()

void 0;