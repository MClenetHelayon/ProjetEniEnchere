<%@ page language="java" contentType="text/javascript; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
"use strict";

function $(className, index = 0) {
	return document.getElementsByClassName(className)[index];
};

class Script {
	
	constructor() {
		this.getTitle = location.pathname;
		this.lastIndex = this.getTitle.lastIndexOf('/');
		this.pathName = this.getTitle.slice(this.lastIndex + 1);
		this.getPathName = this.pathName == "" ? "ServletAccueil" : this.pathName;
		this.is_function(this.getPathName);
		this.menuNav();
	}

	is_function(path) {
		if({}.toString.call(this[path]) === '[object Function]') this[path]();
	}
	
	calculWidthHeader(initWidth) {
		let navMenu = $('header-nav');
		
		if(navMenu !== undefined) {
			let headerLogo = $('header-div-a');
			
			if(headerLogo.clientWidth < 146 && !navMenu.classList.contains(navMenu)) {
				let header = document.getElementsByTagName('header')[0];
				let tailleContainerChildren = 0;
				
				for(let i = 0; i < navMenu.children.length; i++) {
					tailleContainerChildren += navMenu.children[i].clientWidth;
				}
				
				navMenu.classList.add("header-navMenu");
				header.classList.add("headerMenu");
				
				const navMenuStyle = window.getComputedStyle(navMenu, null);
				let navMenuCalcul = parseFloat(navMenuStyle.paddingLeft) + parseFloat(navMenuStyle.paddingRight) + parseFloat(navMenuStyle.rowGap);
				
				const headerStyle = window.getComputedStyle(header, null);
				let headerCalcul = parseFloat(headerStyle.paddingLeft) + parseFloat(headerStyle.paddingRight) + parseFloat(headerStyle.rowGap);
				
				initWidth = navMenuCalcul + headerCalcul + tailleContainerChildren + headerLogo.clientWidth;
				
			} else if(navMenu.classList.contains("header-navMenu") && initWidth <= window.innerWidth) {
				document.getElementsByTagName('header')[0].classList.remove("headerMenu");
				navMenu.classList.remove("header-navMenu");
			}
		}
		
		return initWidth;
	}
	
	menuNav() {
		let initWidth = 0;
		
		initWidth = this.calculWidthHeader(initWidth);
		
		window.addEventListener("resize", () => { initWidth = this.calculWidthHeader(initWidth); });
 	}
 	
	ServletAccueil() {
		document.getElementsByName('achatsVentes').forEach((elem, index) => {
			const groupCheckedSelected = $('filtres-accountConnected-liste-container-checkbox', index).querySelectorAll('input[type="checkbox"]');
			const groupCheckedAll = $('filtres-accountConnected').querySelectorAll('input[type="checkbox"]');
			
			elem.addEventListener(`click`, () => {
				for(let i = 0; i < groupCheckedAll.length; i++) {
					groupCheckedAll[i].checked = false;
					groupCheckedAll[i].disabled = true;
				}
				
				for(let i = 0; i < groupCheckedSelected.length; i++) {
					groupCheckedSelected[i].disabled = false;
				}
		    });
	    });
	    
	  	$('filtres-form-inputSubmit').addEventListener(`click`, () => {
			const infoForm = document.forms.accueilFiltres;
			const nameRadioBox = "achatsVentes";
			let formData = new FormData();
			
			for(let i = 0; i < infoForm.elements.length -1; i++) {
				if((infoForm.elements[i].name == nameRadioBox && infoForm.elements[i].checked) || (infoForm.elements[i].type == "checkbox" && infoForm.elements[i].name == infoForm.elements.achatsVentes.value)) {
					if(infoForm.elements[i].type == "checkbox") {
						formData.append(infoForm.elements[i].name, infoForm.elements[i].checked);
					} else {
						formData.append(infoForm.elements[i].name, infoForm.elements[i].value);
					}
					
				} else if((infoForm.elements[i].name !== nameRadioBox && infoForm.elements[i].type != "checkbox" && infoForm.elements[i].name != infoForm.elements.achatsVentes.value)) {
					formData.append(infoForm.elements[i].name, infoForm.elements[i].value);
				}
			}
			
			fetch(this.getPathName,  {
				method:"post",
				mode: "cors",
				credentials: "same-origin",
				body: formData
			}).then(response => {
				return response.text();
			}).then(body => {
				console.log(body);
			}).catch(err => {
				<% String msgErreur0 = "Erreur lors de la recupération de la requête"; %>
				$('container-erreur').insertAdjacentHTML("afterbegin", `<jsp:include page="/WEB-INF/includes/infoErreur.jsp">
					<jsp:param value="<%= msgErreur0 %>" name="parametre"/>
				</jsp:include>`);
				throw new Error("<%= msgErreur0 %> :\n" + err);
			});
		});
	}
	
	VerifPassword(className) {
		document.getElementById('lblConfirmation').addEventListener('keyup', e => {
			const password = document.getElementById("lblMotDePasse");
			
			for(let i = 0; i < e.target.value.length; i++) {
				if(password.value[i] != e.target.value[i]) {
					$('container-erreur').insertAdjacentHTML("afterbegin", `<jsp:include page="/WEB-INF/includes/infoErreur.jsp">
						<jsp:param value="Mot de passe non correspondant" name="parametre"/>
					</jsp:include>`);
					break;
				}
			}
		});
		
		className.addEventListener(`click`, () => {
			const password = document.getElementById("lblMotDePasse");
			const confirm_password = document.getElementById('lblConfirmation');
			
			if(password.value !== confirm_password.value) {
				<% String msgErreur = "Le mot de passe et la vérification n'est pas correspondant"; %>
				$('container-erreur').insertAdjacentHTML("afterbegin", `<jsp:include page="/WEB-INF/includes/infoErreur.jsp">
					<jsp:param value="<%= msgErreur %>" name="parametre"/>
				</jsp:include>`);
				throw new Error("<%= msgErreur %>");
			}
		});
	}
	
	ServletEditProfil() {
		this.VerifPassword($('editAccount-submit'));
	}
	
	ServletCreationCompte() {
		this.VerifPassword($('createAccount-submit'));
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