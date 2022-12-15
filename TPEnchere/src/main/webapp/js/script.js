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
		document.getElementsByName('achatsVentes').forEach(f => {
			f.addEventListener(`click`, e => {
				let ele = document.getElementsByName('achatsVentes');
		              
		        for(let i = 0; i < ele.length; i++) {
					e.target.parentElement.parentElement.parentElement.querySelectorAll('input[type="checkbox"]').forEach(j => {
						if(ele[i].checked){
							//console.dir(ele[i].parentElement.parentElement.querySelectorAll('input[type="checkbox"]'))
							//j.disabled = false;
						} else {
							//j.disabled = true;
						}
					});
		        }
		    });
	    });
		/*document.getElementsByName('achatsVentes').forEach(f => {
			f.addEventListener(`click`, e => {
				for(i = 0; i < e.length; i++) {
                	if(ele[i].checked) {
						console.log(ele[i].checked)
					}
                }
				/* TODO à corriger */
				//console.log(e.target.parentElement.parentElement.parentElement.querySelectorAll('input[type="checkbox"]'))
				/*e.target.parentElement.parentElement.parentElement.querySelectorAll('input[type="checkbox"]').forEach(j => {
					console.dir(e.target.checked)
					if(j.disabled == true) {
						console.log('ok');
					} else {
						console.log('okokokoko');
						/*j.parentElement.nextSibling.children.forEach(g => {
							g.disabled = true;
						});*\/
					}
				})*\/
			})
		})*/
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